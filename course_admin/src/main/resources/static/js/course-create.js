const URL_API = "/course-create";

const createBtn = document.querySelector(".btn-create-course")

const courseName = document.querySelector("#course-name");
const courseDescription = document.querySelector("#course-description");
const courseType = document.querySelector("#course-type");
const courseTopics = document.querySelector("#course-topic");
const courseSupporter = document.querySelector("#course-supporter");

const postCourseAPI = () => {
    let topicArr = getTopicChose();
    let id = getSupporterId();

    return axios.post(URL_API, {
        name: courseName.value,
        description: courseDescription.value,
        type: courseType.value,
        topics: topicArr,
        supporterId: id
    })
}

const getTopicChose = () => {
    let topicArr = [];
    let topicOptionEl = courseTopics.querySelectorAll("option");
    topicOptionEl.forEach(el => {
        if (el.selected) {
            switch (el.value) {
                case "1":
                    topicArr.push("Backend");
                    break;
                case "2":
                    topicArr.push("Frontend");
                    break;
                case "3":
                    topicArr.push("Mobile");
                    break;
                case "4":
                    topicArr.push("Lập trình web");
                    break;
                case "5":
                    topicArr.push("Database");
                    break;
                case "6":
                    topicArr.push("Devops");
                    break;
            }
        }
    })
    return topicArr;
}

const getSupporterId = () => {
    let supporterId;
    let supporterOptionEl = courseSupporter.querySelectorAll("option");
    supporterOptionEl.forEach(el => {
        if(el.selected){
            supporterId = el.value;
        }
    })
    return supporterId;
}

createBtn.addEventListener("click", async () => {
    try {
        await postCourseAPI();
        toastr.success("Create a successful course!");
    } catch (error) {
        toastr.error(error.response.data.message);
    }
})



