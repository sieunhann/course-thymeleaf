const URL_API = "/course-edit";

const courseName = document.getElementById("course-name");
const courseDescription = document.getElementById("course-description");
const courseType = document.getElementById("course-type");
const courseTopic = $("#course-topic");

const courseSupporter = document.getElementById("course-supporter");
const courseThumbnailPreview = document.getElementById("course-logo-preview");

const updateBtn = document.querySelector(".btn-update-course");

// Kích hoạt select2
courseTopic.select2({
    placeholder: "- Chọn chủ đề",
});

const initCourse = (course) => {
    // courseName.value = course.name;
    // courseDescription.innerText = course.description;
    courseType.value = course.type;
    courseTopic.val(getTopics()).trigger("change");
    courseSupporter.value = course.supporterId;

    // courseThumbnailPreview.src = course.thumnail;
}

const getTopics = () => {
    let topicArr = [];
    course.topics.forEach(el => {
            switch (el) {
                case "Backend":
                    topicArr.push("1");
                    break;
                case "Frontend":
                    topicArr.push("2");
                    break;
                case "Mobile":
                    topicArr.push("3");
                    break;
                case "Lập trình web":
                    topicArr.push("4");
                    break;
                case "Database":
                    topicArr.push("5");
                    break;
                case "Devops":
                    topicArr.push("6");
                    break;
            }
    })
    console.log(topicArr);
    return topicArr;
}

initCourse(course);


const updateCourseAPI = () => {
    return axios.put(`${URL_API}/${course.id}`, {
        name: courseName.value,
        description: courseDescription.value,
        type: courseType.value,
        topics: getUpdateTopics(),
        thumbnail: courseThumbnailPreview.src,
        supporterId: getSupporterId()
    })
}

const getSupporterId = () => {
    let id;
    let supporterOp = courseSupporter.querySelectorAll("option");
    supporterOp.forEach(el => {
        if(el.selected){
            id = el.value;
        }
    })
    return id;
}

const getUpdateTopics = () => {
    let topicArr = [];
    courseTopic.val().forEach(el => {
        switch (el) {
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
    })
    console.log(topicArr);
    return topicArr;
}

updateBtn.addEventListener("click", async () => {
    try {
        console.log(courseTopic.val())
        await updateCourseAPI();
        toastr.success("Successful update");
    } catch (error) {
        toastr.error(error.response.data.message);
    }
})

// Upload anh
const fileUpload = document.querySelector("#course-logo-input");
fileUpload.addEventListener("change", async (event) => {
    try {
        let file = event.target.files[0];

        let formData = new FormData();
        formData.append("file", file);
        let res = await axios.post(`${URL_API}/${course.id}/files`, formData);
        console.log(res.data)
        courseThumbnailPreview.src = res.data;
    } catch (error) {
        toastr.error(error.response.data.message);
    }
})

// Xoa course
const deleteBtn = document.querySelector(".btn-delete-course");
deleteBtn.addEventListener("click", async () => {
    try {
        let isComfirm = confirm("Do you want delete?")
        if(isComfirm) {
            await axios.delete(`${URL_API}/${course.id}`);
            toastr.success("Successful delete")
        }
    } catch (error) {
        console.log(error);
        toastr.error(error.response.data.message);
    }
})