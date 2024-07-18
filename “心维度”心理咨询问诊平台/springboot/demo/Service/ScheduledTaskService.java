package com.example.demo.Service;

import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

// ScheduledTaskService.java
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledTaskService {

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private ConsultantMapper consultantMapper;
    @Autowired
    private ProductionMapper productionMapper;
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CmessageMapper cmessageMapper;
    @Autowired
    private UmessageRepository umessageRepository;
    @Autowired
    private UmessageMapper umessageMapper;
    @Scheduled(fixedRate = 60000) // 每分钟执行一次
    public void checkAndInsertMessages() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, 30);
        Date thirtyMinutesLater = calendar.getTime();
        System.out.println(now);
        System.out.println(thirtyMinutesLater);
        List<Production> productions = productionRepository.findProductionsStartingInNextThirtyMinutes(now, thirtyMinutesLater);

        for (Production production : productions) {
//            System.out.println(production.getStartTime());
            Integer Uid = appointmentMapper.getUid(production.getPid());
            System.out.println(production.getStartTime().before(thirtyMinutesLater) && production.getStartTime().after(now) && production.getMessageState().equals("未发送")&&Uid!=null);
            if (production.getStartTime().before(thirtyMinutesLater) && production.getStartTime().after(now) && production.getMessageState().equals("未发送")&&Uid!=null) {

                String consultantPhoneNumber = consultantMapper.getPhoneNumberById(production.getCid());
                String userPhoneNumber = userMapper.getPhoneNumberById(Uid);
                production.getPid();
                Umessage umessage = new Umessage();
                Cmessage cmessage = new Cmessage();
                System.out.println("插入消息运行");
                cmessage.setContext("咨询者将在30分钟内向您拨打电话进行咨询，具体时间为"+production.getStartTime()+"。咨询者的电话为:"+userPhoneNumber+"。请注意在预约时间接听咨询者电话。祝您工作愉快！");
                cmessage.setCid(production.getCid());
                cmessage.setType("预约提醒");
                cmessage.setCreateTime(new Date());
                umessage.setContext("您的预约将在30分钟内开始，具体时间为"+production.getStartTime()+"。咨询师的电话为:"+consultantPhoneNumber+"。请注意在预约时间拨打咨询师电话。祝您咨询愉快！");
                umessage.setUid(Uid);
                umessage.setType("预约提醒");
                umessage.setCreateTime(new Date());
                umessageMapper.insert(umessage);
                cmessageMapper.insert(cmessage);
                userMapper.setUserSeeMessageState(Uid);
                productionMapper.setProductionMessageState(production.getPid());
//                umessageRepository.save(umessage);
            }
        }
    }
}

