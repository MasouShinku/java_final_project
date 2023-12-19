<template>
    <div class="common-layout">
        <el-container>
            <el-header class="search-header">
                <div class="search-header-side"></div>
                <el-select v-model="selectedLevel" placeholder="选择阶段" size="large">
                    <el-option v-for="item in levelOptions" :key="item.value" :label="item.value" :value="item.value" />
                </el-select>
                <el-select v-model="selectedDifficulity" placeholder="选择难度" size="large">
                    <el-option v-for="item in difficulityOptions" :key="item.value" :label="item.value"
                        :value="item.value" />
                </el-select>
                <div class="search-header-side"></div>
            </el-header>

        </el-container>
    </div>
</template>

<style scoped>
.search-header {
    display: flex;
    justify-content: space-around;
    align-items: center;
    height: 90px;

}


.search-header-side {
    height: 50%;
    width: 10%;
}
</style>
  
  
<script>
import { ref } from 'vue';
export default {
    setup() {


        const questions = ref([]);
        const currentPage = ref(1);
        const displayedQuestions = ref([]);
        const levelOptions = ref([{ value: "🥵不限😍" }, { value: "🥰学前🤗" }, { value: "😁小学🤪" }, { value: "🤔中学🤯" }, { value: "🥴大学😵" }, { value: "😨其他😳" }]);
        const selectedLevel = ref('');
        const difficulityOptions = ref([{ value: "简单" }, { value: "普通" }, { value: "困难" }])
        const selectedDifficulity = ref('');


        const fetch_data = {
            data: [
                {
                    id: 1,
                    title: "题目1",
                    description: "题目1描述",
                    level: "学前",
                    difficulty: "简单"
                }, {
                    id: 2,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 3,
                    title: "题目3",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 4,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 5,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 6,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 7,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 8,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 9,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 10,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 11,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 12,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 13,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }, {
                    id: 14,
                    title: "题目2",
                    description: "题目2描述",
                    level: "小学",
                    difficulty: "困难"
                }

            ]
        };



        questions.value = fetch_data.data;
        displayedQuestions.value = questions.value.slice(0, 10);

        const handleCurrentChange = (newPage) => {
            currentPage.value = newPage;
            var start = newPage * 10 - 10;
            var end = newPage * 10;
            displayedQuestions.value = questions.value.slice(start, end);
        };



        return {
            questions,
            currentPage,
            displayedQuestions,
            levelOptions,
            selectedLevel,
            difficulityOptions,
            selectedDifficulity,
            handleCurrentChange
        };

    }

};
</script>