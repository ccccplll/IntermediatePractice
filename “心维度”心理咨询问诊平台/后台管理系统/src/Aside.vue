<template>
    <div style="height: 100%;">
        <el-menu background-color="#545c64" text-color="#ffffff"
                 active-text-color="#ffd04b" class="el-menu-vertical-demo"
                    router>
            <el-menu-item index="/index/menu1"><i class="el-icon-location"></i>一级菜单1</el-menu-item>
            <el-menu-item index="/index/menu2"><i class="el-icon-document"></i>一级菜单2</el-menu-item>
            <el-menu-item index="/index/menu3"><i class="el-icon-setting"></i>一级菜单3</el-menu-item>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "Aside"
    }
</script>

<style scoped>
    .el-menu-vertical-demo{
        height: 100%;
    }
</style>
