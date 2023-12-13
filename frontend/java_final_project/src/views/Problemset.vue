
<template>
    <div class="common-layout">
        <el-container>
            <el-header class="logo-header">logoHeader</el-header>
            <el-header class="search-header">
                <div class="search-header-side"></div>
                <div class="search-header-center">
                    <el-select v-model="selectedLevel" placeholder="选择阶段" size="large"
                        style="width: 15%;height: 50px;padding-right: 10px;">
                        <el-option v-for="item in levelOptions" :key="item.value" :label="item.value" :value="item.value"
                            style="font-size: 18px;" />
                    </el-select>
                    <el-select v-model="selectedDifficulty" placeholder="选择难度" size="large"
                        style="width: 15%;height: 50px;padding-right: 10px;">
                        <el-option v-for="item in difficultyOptions" :key="item.value" :label="item.value"
                            :value="item.value" style="font-size: 18px;" />
                    </el-select>
                    <el-input v-model="searchKey" placeholder="Please input"
                        style="width: 30%;height: 40px;margin-left: 30px;" />
                    <el-button type="primary" :icon="Search" style="width: 10%;height: 40px;margin-left: 30px;"
                        @click="searchQuestions">Search</el-button>
                    <el-button type="primary" :icon="Upload" style="width: 10%;height: 40px;margin-left: 30px;"
                        @click="UploadQuestion">上传题目</el-button>
                </div>
                <div class="search-header-side"></div>
            </el-header>
            <!-- <el-header class="part-description">
                part-description
            </el-header> -->
            <el-main class="main">
                <div class="questions">
                    <div class="questions-left"></div>
                    <div class="questions-center">
                        <div class="question-top">
                            <div class="question-title">
                                <el-text truncated tag="b"
                                    style="font-size: 22px;margin-left: 10%;margin-right: 10%;">标题</el-text>
                            </div>
                            <div class="question-description">
                                <el-text truncated tag="b"
                                    style="font-size: 22px;margin-left: 10%;margin-right: 10%;">题目描述</el-text>
                            </div>
                            <div class="question-level">
                                <el-text truncated tag="b"
                                    style="font-size: 22px;margin-left: 10%;margin-right: 10%;">阶段</el-text>
                            </div>
                            <div class="question-difficulty">
                                <el-text truncated tag="b"
                                    style="font-size: 22px;margin-left: 10%;margin-right: 10%;">难度</el-text>
                            </div>
                        </div>
                        <div class="question" v-for="(question, index) in displayedQuestions" :key="index"
                            :class="{ 'bg-black': index % 2 === 0, 'bg-white': index % 2 !== 0 }"
                            @click="toDetail(question.id)">
                            <div class="question-title">
                                <el-text truncated style="font-size: 20px;margin-left: 10%;margin-right: 10%;">{{
                                    question.title }}</el-text>
                            </div>
                            <div class="question-description"> <el-text truncated
                                    style="font-size: 20px;margin-left: 10%;margin-right: 10%;">{{
                                        question.description }}</el-text></div>
                            <div class="question-level"> <el-text truncated
                                    style="font-size: 20px;margin-left: 10%;margin-right: 10%;">{{
                                        question.level }}</el-text></div>
                            <div class="question-difficulty"> <el-text truncated
                                    style="font-size: 20px;margin-left: 10%;margin-right: 10%;">{{
                                        question.difficulty }}</el-text></div>
                        </div>
                    </div>
                    <div class="questions-right"></div>
                </div>
                <div class="pages">
                    <el-pagination class="pagination" layout="prev, pager, next" :total=questions.length
                        @current-change="handleCurrentChange" />
                </div>
            </el-main>
        </el-container>
    </div>
</template>


  
  
  
<style scoped>
.common-layout {
    background: linear-gradient(to right, rgba(255, 182, 193, 0.25), rgba(135, 206, 250, 0.15));
    display: flex;
    height: 100vh;
    width: 100vw;
}

.logo-header {
    background-color: rgb(255, 255, 255);
    height: 8%;

}

.search-header {
    display: flex;
    justify-content: space-around;
    align-items: center;
    height: 90px;

}

.level-button {
    height: 60%;
    width: 12%;
    color: black;
    font-size: 24px;

}

.level-button.is-active,
.level-button:active {
    background: rgb(0, 0, 0);
    border-color: rgb(0, 0, 0);
    color: #ffffff;
}

.search-header-side {
    height: 80%;
    width: 20%;
}

.search-header-center {
    display: flex;
    justify-content: center;
    height: 80%;
    width: 80%;
    padding-top: 40px;
}

.questions {
    display: flex;
    height: 90%;

}



.questions-left,
.questions-right {
    height: 100%;
    width: 15%;
    /* background-color: aqua; */

}

.questions-center {
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 70%;
}

.question-top {
    display: flex;
    width: 100%;
    height: 10%;
    padding: 3px;

    background-color: bisque;

}

.question {
    display: flex;
    width: 100%;
    height: 10%;
    padding: 3px;

}

.bg-black {
    background-color: rgb(236, 236, 236);
}

.bg-white {
    background-color: white;
}

.question-title {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 20%;
}

.question-description {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 50%;
}

.question-level {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 15%;
}

.question-difficulty {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 15%;
}

.pages {
    display: flex;
    justify-content: space-around;
    align-items: center;
}
</style>
  
  
<script>
import { ref, onMounted } from 'vue';
import { Search, Upload } from '@element-plus/icons-vue'
import { useQuestionidStore } from '../stores/questionid'
import { useRouter } from 'vue-router'
import axios from 'axios'
// import { axios } from 'axios'
export default {
    setup() {

        const questonidStore = useQuestionidStore()
        // 以后通过questonidStore.questionid直接访问当前选择的题目id即可
        console.log(questonidStore.questionid)


        const router = useRouter()
        const questions = ref([]);
        const currentPage = ref(1);
        const displayedQuestions = ref([]);
        const levelOptions = ref([{ value: "🥵不限😤" }, { value: "🥰学前😻" }, { value: "😁小学🤪" }, { value: "🤔中学😼" }, { value: "🥴大学😵" }, { value: "😨其他😳" }]);
        const selectedLevel = ref('🥵不限😤');
        const difficultyOptions = ref([{ value: "😯不限😺" }, { value: "🤤简单😎" }, { value: "😯普通🤯" }, { value: "😈困难🙀" }])
        const selectedDifficulty = ref('😯不限😺');
        const searchKey = ref('');

        const levelArr = [-1, 1, 2, 3, 4, 5]
        const difficultyArr = [-1, 1, 2, 3, 4]


        // const fetch_data = {
        //     data: [
        //         {
        //             id: 1,
        //             title: "题目1",
        //             description: "题目1描述",
        //             level: "学前",
        //             difficulty: "简单"
        //         }, {
        //             id: 2,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 3,
        //             title: "题目3",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 4,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 5,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 6,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 7,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 8,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 9,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 10,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 11,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 12,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 13,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }, {
        //             id: 14,
        //             title: "题目2",
        //             description: "题目2描述",
        //             level: "小学",
        //             difficulty: "困难"
        //         }

        //     ]
        // };



        // questions.value = fetch_data.data;

        // onMounted(async () => {
        //     // 进入界面时候先请求一次


        // });

        // 进入界面时候先请求一次
        const loadData = () => {
            axios.post('http://localhost:8080/api/question/search', {
                level: -1,
                difficulty: -1,
                searchKey: ""
            })
                .then(response => {
                    // 处理一下逻辑
                    console.log("response.data is ")
                    console.log(response.data);
                    questions.value = response.data
                    displayedQuestions.value = questions.value.slice(0, 10);
                })
                .catch(error => {
                    console.log(error);
                });
        }

        onMounted(() => {
            loadData();
        });




        console.log("displayedQuestions.value is")
        console.log(displayedQuestions.value)


        // 管理翻页的函数
        const handleCurrentChange = (newPage) => {
            currentPage.value = newPage;
            var start = newPage * 10 - 10;
            var end = newPage * 10;
            displayedQuestions.value = questions.value.slice(start, end);
        };

        // 进行题目搜索的函数
        const searchQuestions = async () => {
            for (let i = 0; i < levelOptions.value.length; i++) {
                if (levelOptions.value[i].value == selectedLevel.value)
                    var levelValue = levelArr[i]
                // console.log("level : " + levelArr[i])
            }
            for (let i = 0; i < difficultyOptions.value.length; i++) {

                if (difficultyOptions.value[i].value == selectedDifficulty.value)
                    var difficultyValue = difficultyArr[i]
                // console.log("difficulty : " + difficultyArr[i])
            }
            // console.log("level: " + selectedLevel.value)
            // console.log("difficulty: " + selectedDifficulty.value)
            // console.log("key: " + searchKey.value)


            axios.post('http://localhost:8080/api/question/search', {
                level: levelValue,
                difficulty: difficultyValue,
                searchKey: searchKey.value
            })
                .then(response => {
                    // 处理一下逻辑
                    console.log("response.data is ")
                    console.log(response.data);
                    questions.value = response.data
                    displayedQuestions.value = questions.value.slice(0, 10);
                })
                .catch(error => {
                    console.log(error);
                });

        }


        // 跳转到详情页
        const toDetail = (id) => {
            questonidStore.setId(id)
            localStorage.setItem('questionId', id);
            console.log("current id is : " + id)
            console.log("local id is " + localStorage.getItem("questionId"))
            router.push('/detail')
        }


        // 跳转到上传页
        const UploadQuestion = () => {
            router.push('/upload')
        }





        return {
            questions,
            currentPage,
            displayedQuestions,
            levelOptions,
            selectedLevel,
            difficultyOptions,
            selectedDifficulty,
            searchKey,
            questonidStore,
            Upload,
            Search,
            searchQuestions,
            handleCurrentChange,
            toDetail,
            UploadQuestion
        };

    }

};
</script>