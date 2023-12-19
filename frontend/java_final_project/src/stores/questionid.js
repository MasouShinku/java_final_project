import { ref } from "vue"
import { defineStore } from "pinia"


export const useQuestionidStore = defineStore('questionid', () => {
    const questionid = ref(0);
    function setId(newId) {
        questionid.value = newId
    }

    return { questionid, setId }

})