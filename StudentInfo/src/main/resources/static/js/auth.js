// 登录验证
function checkAuth() {
    const user = localStorage.getItem('user');
    if (!user) {
        window.location.href = 'index.html';
        return null;
    }
    return JSON.parse(user);
}

function logout() {
    localStorage.removeItem('user');
    window.location.href = 'index.html';
}

function getUser() {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
}

// 检查是否是老师
function isTeacher() {
    const user = getUser();
    return user && user.role === 'teacher';
}

// 检查是否是学生
function isStudent() {
    const user = getUser();
    return user && user.role === 'student';
}

// 检查页面访问权限（老师专属页面）
function checkTeacherOnly() {
    if (!isTeacher()) {
        alert('您没有权限访问此页面');
        window.location.href = 'dashboard.html';
        return false;
    }
    return true;
}
