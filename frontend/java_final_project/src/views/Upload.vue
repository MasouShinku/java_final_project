
<template>
    <div class="common-layout">
        <el-container>
            <el-header class="logo-header"
                style="background-color: rgba(0, 0, 0, 0.5);display:flex;justify-content: center;">
                <img src="../../public/img/java_logo.png" style="max-height: 100%; width: auto;">
            </el-header>


            <el-main class="main">

                <div class="main-left"></div>
                <div class="main-center">
                    <el-card class="question-card" style="padding-left: 50px;padding-right: 50px;">
                        <template #header>
                            <div>
                                <h1 style="text-align: center;font-size: 40px;font-family: '微软雅黑';margin-bottom: 0;">
                                    上传题目</h1>
                            </div>
                        </template>
                        <div style="padding-bottom: 50px;padding-right: 100px;">
                            <el-form :model="form" label-width="120px" style="font-family: '微软雅黑';">
                                <el-form-item label="题目名称(必填)">
                                    <el-input v-model="form.questionName" maxlength="20" />
                                </el-form-item>
                                <el-form-item label="难度(必填)">
                                    <el-radio-group v-model="form.level">
                                        <div v-for="levelItem in levelOptions">
                                            <el-radio :label="levelItem.value" style="margin-right: 30px;" />
                                        </div>

                                        <!-- <el-radio label="简单" />
                                        <el-radio label="普通" />
                                        <el-radio label="困难" /> -->
                                    </el-radio-group>
                                </el-form-item>

                                <el-form-item label="等级(必填)">
                                    <el-radio-group v-model="form.difficulty">
                                        <div v-for="difficultyItem in difficultyOptions">
                                            <el-radio :label="difficultyItem.value" style="margin-right: 30px;" />
                                        </div>
                                        <!-- <el-radio label="学前" />
                                        <el-radio label="小学" />
                                        <el-radio label="中学" />
                                        <el-radio label="大学" /> -->
                                    </el-radio-group>
                                </el-form-item>


                                <el-form-item label="题目描述(必填)">
                                    <el-input v-model="form.desc" type="textarea"
                                        :autosize="{ minRows: 10, maxRows: 10 }" />
                                </el-form-item>





                                <el-form-item label="相关媒体">
                                    <el-upload v-model:file-list="fileList" class="upload-demo" action="" multiple
                                        :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove"
                                        :limit="3" :on-exceed="handleExceed" :auto-upload="false">
                                        <el-button type="primary">Click to upload</el-button>
                                        <template #tip>
                                            <div class="el-upload__tip">
                                                支持jpg/png/mp4/mp3
                                            </div>
                                        </template>
                                    </el-upload>
                                </el-form-item>


                                <el-form-item>
                                    <el-button type="primary" @click="onSubmit">发布！</el-button>
                                </el-form-item>


                            </el-form>

                        </div>

                    </el-card>
                </div>
                <div class="main-right" style="padding-top: 60px;padding-right: 50px;height: 90%;">
                </div>

            </el-main>
        </el-container>
    </div>
</template>


  
  
  
<style scoped>
.common-layout {
    background: linear-gradient(to right, rgba(255, 182, 193, 0.4), rgba(135, 206, 250, 0.4));
    display: flex;
    height: 100vh;
    width: 100vw;
}

.logo-header {
    background-color: rgb(255, 255, 255);
    height: 10%;

}

.main {
    display: flex;
    height: 90%;
    width: 100%;
    padding: 0;
}



.main-left,
.main-right {
    height: 100%;
    width: 25%;

}

.main-center {
    height: 95%;
    width: 50%;

}

.question-card {
    margin: 20px;
    height: 100%;
    overflow-y: auto;
    margin-bottom: 0;

}


.scrollbar-demo-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    margin: 20px;
    margin-top: 0;
    text-align: center;
    border-radius: 4px;
    /* background: var(--el-color-primary-light-9);
    color: var(--el-color-primary); */
    background-color: rgb(238, 238, 238);
}
</style>
  
  
<script>
import { onMounted, ref, reactive, markRaw } from 'vue';
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus';
import { SuccessFilled, CircleCloseFilled } from '@element-plus/icons-vue'
// import { configProviderContextKey } from 'element-plus';
export default {
    setup() {

        axios.defaults.headers.common['satoken'] = localStorage.getItem('satoken');
        axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

        const form = reactive({
            questionName: '',
            level: '',
            difficulty: '',
            desc: '',
        })



        const levelOptions = [{ value: "🥰学前😻" }, { value: "😁小学🤪" }, { value: "🤔中学😼" }, { value: "🥴大学😵" }, { value: "😨其他😳" }];
        const difficultyOptions = [{ value: "🤤简单😎" }, { value: "😯普通🤯" }, { value: "😈困难🙀" }];


        // 检查表单是否有空选项
        const isWhitespace = (str) => {
            return str.trim() === '';
        };

        const checkForm = () => {
            if (isWhitespace(form.desc) || form.difficulty == '' || form.level == '' || form.questionName == '')
                return false
            return true
        }

        const onSubmit = async () => {
            if (checkForm() == false)
                ElMessage.error("表单中不能有空选项")
            else if (fileTest() == false) {
                ElMessage.error("有非法的文件拓展名")
            }
            else {
                // 先上传表单
                console.log(form.desc)
                try {
                    const formData = new FormData();
                    formData.append('level', form.level)
                    formData.append('difficulty', form.difficulty)
                    formData.append('title', form.questionName)
                    formData.append('description', form.desc)
                    for (const file of fileList.value) {
                        formData.append('fileList', file.raw)
                    }
                    console.log(formData)
                    const response = await axios.post('http://localhost:8080/api/question/upload', formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data',
                            "satoken": localStorage.getItem('satoken')
                        }
                    })
                    if (response.data.message == 'success') {
                        ElMessageBox.confirm('发布成功！', '提示', {
                            type: 'success',
                            icon: markRaw(SuccessFilled),
                        })
                        setTimeout(() => {
                            window.location.reload();
                        }, 2000);
                    }
                    console.log(response.data)
                } catch (error) {
                    console.error(error);
                    ElMessageBox.confirm('发布失败！', '提示', {
                        type: 'danger',
                        icon: markRaw(CircleCloseFilled),
                    })
                }
            }



        }




        const fileList = ref([
        ]);

        // 检查文件格式
        const fileTest = () => {

            const allowedExtensions = ['.jpg', '.png', '.mp4', '.mp3'];

            for (const file of fileList.value) {
                const fileName = file.name.toLowerCase();
                const isValidExtension = allowedExtensions.some(ext => fileName.endsWith(ext));

                if (!isValidExtension) {
                    console.log(false)
                    return false; // 如果有一个文件类型不匹配，返回 false
                }
            }

            console.log(true)

            return true; // 所有文件类型都匹配，返回 true

        }



        onMounted(() => {
        });



        return {
            form,
            levelOptions,
            difficultyOptions,
            fileList,
            fileTest,
            onSubmit
        };

    }

};
</script>