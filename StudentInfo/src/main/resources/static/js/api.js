// API 封装
const BASE_URL = '/api';

const api = {
    // 认证
    login: (name, password) => axios.post(`${BASE_URL}/auth/login`, { name, password }),
    register: (name, password) => axios.post(`${BASE_URL}/auth/register`, { name, password }),

    // 学生
    getStudents: () => axios.get(`${BASE_URL}/students`),
    getStudent: (sno) => axios.get(`${BASE_URL}/students/${sno}`),
    addStudent: (data) => axios.post(`${BASE_URL}/students`, data),
    updateStudent: (sno, data) => axios.put(`${BASE_URL}/students/${sno}`, data),
    deleteStudent: (sno) => axios.delete(`${BASE_URL}/students/${sno}`),
    countStudents: () => axios.get(`${BASE_URL}/students/count`),

    // 课程
    getCourses: () => axios.get(`${BASE_URL}/courses`),
    getCourse: (cno) => axios.get(`${BASE_URL}/courses/${cno}`),
    addCourse: (data) => axios.post(`${BASE_URL}/courses`, data),
    updateCourse: (cno, data) => axios.put(`${BASE_URL}/courses/${cno}`, data),
    deleteCourse: (cno) => axios.delete(`${BASE_URL}/courses/${cno}`),

    // 选课
    getSelections: () => axios.get(`${BASE_URL}/sc`),
    getStudentSelections: (sno) => axios.get(`${BASE_URL}/sc/student/${sno}`),
    getCourseSelections: (cno) => axios.get(`${BASE_URL}/sc/${cno}`),
    select: (data) => axios.post(`${BASE_URL}/sc`, data),
    drop: (sno, cno) => axios.delete(`${BASE_URL}/sc/${sno}/${cno}`),
    updateGrade: (sno, cno, grade) => axios.post(`${BASE_URL}/sc/${sno}/${cno}/grade`, { grade })
};
