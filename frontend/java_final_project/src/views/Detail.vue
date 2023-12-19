
<template>
    <div class="common-layout">
        <el-container>
            <el-header class="logo-header"
                style="background-color: rgba(0, 0, 0, 0.5);display:flex;justify-content: center;">
                <img src="../../public/img/java_logo.png" style="max-height: 100%; width: auto;">
            </el-header>


            <el-main class="main">

                <div class="main-left">
                    <div class="model-area">
                        <img src="../../public/img/model.png" alt="" class="model-image"
                            style="max-width: 90%;height: auto;padding-left: 5%;padding-top: 40%;">
                    </div>
                </div>
                <div class="main-center">
                    <el-card class="question-card" v-if="questionDetail != null"
                        style="padding-left: 50px;padding-right: 50px;">
                        <template #header>
                            <div>
                                <h1 style="text-align: center;font-size: 40px;font-family: '微软雅黑';margin-bottom: 0;">
                                    {{ questionDetail.title }}</h1>
                                <div
                                    style="display: flex; justify-content: center; align-items: center; flex-direction: row;">
                                    <h4
                                        style="color:white;background-color:rgb(61, 60, 60);width:80px;border-radius:5px;margin-bottom: 10px;margin-right: 50px;">
                                        {{
                                            questionDetail.level }}
                                    </h4>
                                    <h4
                                        style="color:white;background-color:rgb(61, 60, 60);width:80px;border-radius:5px;margin-bottom: 10px;">
                                        {{
                                            questionDetail.difficulty }}
                                    </h4>
                                </div>
                            </div>
                        </template>
                        <div style="padding-bottom: 50px;">
                            <div>
                                <h2 style="font-size: 20px;font-family: '微软雅黑';">题目描述</h2>
                                <p style="font-family: '微软雅黑';">{{ questionDetail.description }}</p>
                            </div>
                            <div v-if="questionDetail.photoUrl.length != 0" style="margin-top: 30px;">
                                <h2 style="font-size: 20px;font-family: '微软雅黑';">相关图片</h2>
                                <div v-for="photo in questionDetail.photoUrl"
                                    style="display:flex; justify-content: center;align-items: center;margin-bottom: 20px;">
                                    <img :src="photo" style="width: 70%; height: auto;">
                                </div>
                                <!-- <p style="font-family: '黑体';">{{ questionDetail.photoUrl }}</p> -->
                            </div>
                            <div v-if="questionDetail.videoUrl.length != 0" style="margin-top: 30px;">
                                <h2 style="font-size: 20px;font-family: '微软雅黑';">相关视频</h2>
                                <div v-for="video in questionDetail.videoUrl"
                                    style="display:flex; justify-content: center;align-items: center;margin-bottom: 20px;">
                                    <video :src="video" style="width: 70%; height: auto;" controls></video>
                                </div>
                                <!-- <p style="font-family: '黑体';">{{ questionDetail.videoUrl }}</p> -->
                                <!-- <div v-for="i in 100">{{ i }}</div> -->
                            </div>
                            <div v-if="questionDetail.audioUrl.length != 0" style="margin-top: 30px;">
                                <h2 style="font-size: 20px;font-family: '微软雅黑';">相关音频</h2>
                                <div v-for="audio in questionDetail.audioUrl"
                                    style="display:flex; justify-content: center;align-items: center;margin-bottom: 20px;">
                                    <audio :src="audio" style="width: 70%; height: auto;" controls></audio>
                                </div>
                                <!-- <p style="font-family: '黑体';">{{ questionDetail.audioUrl }}</p> -->

                            </div>
                            <div style="display: flex;text-align: center;justify-content: center;">
                                <el-button type="primary" :icon="Upload" style="width: 20%;height: 40px;"
                                    @click="exportQuestion">
                                    导出题目
                                </el-button>
                            </div>
                        </div>

                    </el-card>
                </div>
                <div class=" main-right" style="padding-top: 60px;padding-right: 50px;height: 90%;">
                    <el-card class="box-card" style="height: 80%;padding-bottom: 50px;">
                        <template #header style="display: flex;">
                            <h1
                                style="text-align: center;font-size: 26px;font-family: '微软雅黑';margin-bottom: 10px;margin-top: 10px;">
                                讨论区</h1>
                            <div style="text-align: center;">
                                <el-button type="primary" :icon="Upload" style="width: 25%;height: 30px;"
                                    @click="dialogFormVisible = true">发布评论</el-button>
                            </div>
                            <el-dialog v-model="dialogFormVisible" title="Shipping address" style="width: 700px;">
                                <el-form :model="form">
                                    <el-form-item label="评论内容" label-width="100px">
                                        <el-input v-model="form.text" autocomplete="off" style="width: 500px;" :rows="5"
                                            type="textarea" />
                                    </el-form-item>
                                </el-form>
                                <template #footer>
                                    <span class="dialog-footer">
                                        <el-button @click="dialogFormVisible = false; form.text = ''">取消</el-button>
                                        <el-button type="primary" @click="dialogFormVisible = false; PublishComment()">
                                            确认
                                        </el-button>
                                    </span>
                                </template>
                            </el-dialog>

                        </template>
                        <el-scrollbar height="400px" margin-bottom="50px">
                            <p v-for="item in commentList" :key="item" class="scrollbar-demo-item"
                                style="font-size: 16px;font-family: '微软雅黑';">{{ item.text }}
                            </p>
                        </el-scrollbar>
                        <template #footer>

                        </template>
                    </el-card>

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
    padding-top: 15px;
    padding-bottom: 15px;
    padding-left: 15px;
    padding-right: 15px;
    height: auto;
    margin: 20px;
    margin-top: 0;
    text-align: center;
    border-radius: 4px;
    background-color: rgb(238, 238, 238);
}

.model-image:hover {
    transform: scale(1.03);
}
</style>
  
  
<script>
import { onMounted, ref, reactive, markRaw } from 'vue';
import { useQuestionidStore } from '../stores/questionid'
import { ElMessage, ElMessageBox } from 'element-plus';
import { Upload } from '@element-plus/icons-vue'
import { SuccessFilled, CircleCloseFilled } from '@element-plus/icons-vue'
import axios from 'axios'
// import { configProviderContextKey } from 'element-plus';
export default {
    setup() {


        const questonidStore = useQuestionidStore()


        const questionDetail = ref(null);
        const commentList = ref(null);


        // 进入界面时候先请求一次
        const loadData = async () => {
            console.log("id is " + questonidStore.questionid)
            // 请求一下题目详细信息
            try {
                console.log(questonidStore.questionid);
                const response = await axios.get('http://localhost:8080/api/question/detail?id=' + localStorage.getItem("questionId"))
                questionDetail.value = response.data;
                console.log(response.data)
            } catch (error) {
                console.error(error);
            }


            // 请求一下评论
            try {
                console.log(questonidStore.questionid);
                const response = await axios.get('http://localhost:8080/api/comment/getComment?id=' + localStorage.getItem("questionId"))
                commentList.value = response.data.getCommentResponseList;
                console.log(response.data)
            } catch (error) {
                console.error(error);
            }


        }

        //      发布评论的对话框
        const dialogFormVisible = ref(false)

        const form = reactive({
            text: '',
        })

        const isWhitespace = (str) => {
            return str.trim() === '';
        };


        const checkForm = () => {
            if (isWhitespace(form.text))
                return false
            return true
        }


        const PublishComment = async () => {

            if (checkForm() == false) {
                ElMessage.error("不可以发布空评论")
            }
            else {
                const commentData = {
                    text: form.text,
                    questionId: localStorage.getItem('questionId')
                }
                try {
                    console.log(commentData)
                    const response = await axios.post('http://localhost:8080/api/comment/publishComment', commentData, {
                        headers: {
                            "satoken": localStorage.getItem('satoken')
                        }
                    })
                    if (response.data.message == 'success') {
                        form.text = '',
                            ElMessageBox.confirm('发布成功！', '提示', {
                                type: 'success',
                                icon: markRaw(SuccessFilled),
                            }),
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


        const exportQuestion = () => {

            var detail = `标题 ${questionDetail.value.title}<br>
                题目描述 ${questionDetail.value.description}<br>
                  难度 ${questionDetail.value.difficulty}<br>
                  等级 ${questionDetail.value.level}`;
            ElMessageBox.alert(
                detail,
                '请自己复制',
                {
                    dangerouslyUseHTMLString: true,
                }
            )

        }

        onMounted(() => {
            loadData();
        });




        return {
            Upload,
            questionDetail,
            dialogFormVisible,
            form,
            commentList,
            PublishComment,
            exportQuestion
        };

    }

};
</script>