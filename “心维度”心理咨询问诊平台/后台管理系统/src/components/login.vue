<template>
  <div>
    <p class="tip">Click on button in image container</p>
    <div class="cont" :class="{ 's--signup': isSignUp }">
      <div class="form sign-in">
        <h2>Welcome back,</h2>
        <label>
          <span>Email</span>
          <input type="email" v-model="email" />
        </label>
        <label>
          <span>Password</span>
          <input type="password"  v-model="password"/>
        </label>
        <p class="forgot-pass">Forgot password?</p>
        <button type="button" @click="click_login"  class="submit">Sign In</button>
        <button type="button" class="fb-btn">Connect with <span>facebook</span></button>
      </div>
      <div class="sub-cont">
        <div class="img">
          <div class="img__text m--up">
            <h2>New here?</h2>
            <p>Sign up and discover great amount of new opportunities!</p>
          </div>
          <div class="img__text m--in">
            <h2>One of us?</h2>
            <p>If you already has an account, just sign in. We've missed you!</p>
          </div>
          <div class="img__btn">
            <span class="m--up">Sign Up</span>
            <span class="m--in">Sign In</span>
          </div>
        </div>
        <div class="form sign-up">
          <h2>Time to feel like home,</h2>
          <label>
            <span>Email</span>
            <input type="email" v-model="email" />
          </label>
          <label>
            <span>Password</span>
            <input type="password" v-model="password" />
          </label>
          <button type="button" @click="click_regisiter"  class="submit">Sign Up</button>
          <button type="button" class="fb-btn">Join with <span>facebook</span></button>
        </div>
      </div>
    </div>

    <a href="https://dribbble.com/shots/3306190-Login-Registration-form" target="_blank" class="icon-link">
      <img src="http://icons.iconarchive.com/icons/uiconstock/socialmedia/256/Dribbble-icon.png">
    </a>
   
    <a href="https://twitter.com/NikolayTalanov" target="_blank" class="icon-link icon-link--twitter">
      <img src="https://cdn1.iconfinder.com/data/icons/logotypes/32/twitter-128.png">
    </a>
  </div>
</template>

<script>
	import axios from 'axios';
export default {
  data() {
    return {
      isSignUp: false,
	  email:"admin1@example.com",
	  password:"admin1pwd"
    };
  },
  mounted() {
    document.querySelector('.img__btn').addEventListener('click', () => {
      this.isSignUp = !this.isSignUp;
    });
  },
   methods: {
    click_login() { 
		const self = this;
		      axios.post('http://localhost:3000/api/administrator/queryAdministrator', { email: this.email })
		        .then(function(response) {
		          if (response.data && response.data.length > 0) {
		            if (response.data[0].password !== self.password) {
		              console.log('Error');
		            } else {
		              // 假设这是设置全局状态的方法
		              //self.$store.commit('setEmail', self.email);
		
		              // 触发名为 'cityStatusChanged' 的自定义事件
		              //self.$emit('cityStatusChanged');
		
		              // 路由跳转到指定路径
		              self.$router.push('/SystemIndex');
		            }
		          } else {
		            // 用户名错误提示
		            self.$toasted.error('用户名错误', { duration: 2000 });
		          }
		        })
		        .catch(function(error) {
		          console.log(error);
		          // 网络请求失败提示
		          self.$toasted.error('网络请求失败', { duration: 2000 });
		        });
    },
	click_regisiter(){
		 var email = this.email;
		      var password = this.password;
		       axios.post('http://localhost:3000/api/administrator/addAdministrator', {
		                email: email,
		                password: password
		              }, {}).then((response) => { console.log(response); })
	},
		        
	changeMsg () {
		              axios.get('http://localhost:3000/api/administrator/query').then((response) => {
		                  console.log(response)
		                  console.log('--------')
		              })
		            },
		     
	},
}


</script>

<style scoped lang="scss">
$contW: 900px;
$imgW: 260px;
$formW: $contW - $imgW;
$switchAT: 1.2s;

$inputW: 260px;
$btnH: 36px;

$diffRatio: ($contW - $imgW) / $contW;

@mixin signUpActive {
  .cont.s--signup & {
    @content;
  }
}

.tip {
  font-size: 20px;
  margin: 40px auto 50px;
  text-align: center;
}

.cont {
  overflow: hidden;
  position: relative;
  width: $contW;
  height: 550px;
  margin: 0 auto 100px;
  background: #fff;
}

.form {
  position: relative;
  width: $formW;
  height: 100%;
  transition: transform $switchAT ease-in-out;
  padding: 50px 30px 0;
}

.sub-cont {
  overflow: hidden;
  position: absolute;
  left: $formW;
  top: 0;
  width: $contW;
  height: 100%;
  padding-left: $imgW;
  background: #fff;
  transition: transform $switchAT ease-in-out;

  @include signUpActive {
    transform: translate3d($formW * -1, 0, 0);
  }
}

button {
  display: block;
  margin: 0 auto;
  width: $inputW;
  height: $btnH;
  border-radius: 30px;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
}

.img {
  overflow: hidden;
  z-index: 2;
  position: absolute;
  left: 0;
  top: 0;
  width: $imgW;
  height: 100%;
  padding-top: 360px;

  &:before {
    content: '';
    position: absolute;
    right: 0;
    top: 0;
    width: $contW;
    height: 100%;
    background-image: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/sections-3.jpg');
    background-size: cover;
    transition: transform $switchAT ease-in-out;
  }

  &:after {
    content: '';
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.6);
  }

  @include signUpActive {
    &:before {
      transform: translate3d($formW, 0, 0);
    }
  }

  &__text {
    z-index: 2;
    position: absolute;
    left: 0;
    top: 50px;
    width: 100%;
    padding: 0 20px;
    text-align: center;
    color: #fff;
    transition: transform $switchAT ease-in-out;

    h2 {
      margin-bottom: 10px;
      font-weight: normal;
    }

    p {
      font-size: 14px;
      line-height: 1.5;
    }

    &.m--up {
      @include signUpActive {
        transform: translateX($imgW * 2);
      }
    }

    &.m--in {
      transform: translateX($imgW * -2);

      @include signUpActive {
        transform: translateX(0);
      }
    }
  }

  &__btn {
    overflow: hidden;
    z-index: 2;
    position: relative;
    width: 100px;
    height: $btnH;
    margin: 0 auto;
    background: transparent;
    color: #fff;
    text-transform: uppercase;
    font-size: 15px;
    cursor: pointer;

    &:after {
      content: '';
      z-index: 2;
      position: absolute;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      border: 2px solid #fff;
      border-radius: 30px;
    }

    span {
      position: absolute;
      left: 0;
      top: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      width: 100%;
      height: 100%;
      transition: transform $switchAT;

      &.m--in {
        transform: translateY($btnH * -2);

        @include signUpActive {
          transform: translateY(0);
        }
      }

      &.m--up {
        @include signUpActive {
          transform: translateY($btnH * 2);
        }
      }
    }
  }
}

h2 {
  width: 100%;
  font-size: 26px;
  text-align: center;
}

label {
  display: block;
  width: $inputW;
  margin: 25px auto 0;
  text-align: center;

  span {
    font-size: 12px;
    color: #cfcfcf;
    text-transform: uppercase;
  }
}

input {
  display: block;
  width: 100%;
  margin-top: 5px;
  padding-bottom: 5px;
  font-size: 16px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.4);
  text-align: center;
}

.forgot-pass {
  margin-top: 15px;
  text-align: center;
  font-size: 12px;
  color: #cfcfcf;
}

.submit {
  margin-top: 40px;
  margin-bottom: 20px;
  background: #d4af7a;
  transition: background 0.3s;
}

.fb-btn {
  background: #4968ad;
  margin-bottom: 20px;
  transition: background 0.3s;

  &:hover {
    background: darken(#4968ad, 10%);
  }
}

.icon-link {
  display: inline-block;
  width: 50px;
  height: 50px;
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 100;

  img {
    width: 100%;
    height: auto;
  }
}

.link-footer {
  display: inline-block;
  width: 150px;
  height: 50px;
  position: fixed;
  bottom: 20px;
  left: 20px;
  z-index: 100;
}

.icon-link--twitter {
  bottom: 90px;
}

@media screen and (max-width: 900px) {
  .cont {
    height: auto;
    margin-bottom: 50px;
  }

  .form,
  .sub-cont {
    width: 100%;
    padding: 30px 25px 0;
  }

  .img {
    height: 350px;
    padding-top: 260px;
    padding-bottom: 50px;
  }

  .icon-link,
  .link-footer {
    display: none;
  }
}
</style>
