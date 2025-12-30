// 公共函数

// 显示提示消息
function showToast(message, type = 'success') {
    const toastContainer = document.getElementById('toastContainer');
    if (!toastContainer) return;

    const toast = document.createElement('div');
    toast.className = `toast show align-items-center text-white bg-${type} border-0`;
    toast.innerHTML = `
        <div class="d-flex">
            <div class="toast-body">${message}</div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
        </div>
    `;
    toastContainer.appendChild(toast);

    setTimeout(() => toast.remove(), 3000);
}

// 显示确认对话框
function showConfirm(message) {
    return confirm(message);
}

// 格式化日期
function formatDate(dateStr) {
    if (!dateStr) return '-';
    return dateStr;
}

// 处理 API 错误
function handleError(error) {
    console.error(error);
    const msg = error.response?.data?.message || '操作失败';
    showToast(msg, 'danger');
}

// 侧边栏 HTML 模板（根据角色显示不同菜单）
function getSidebarHTML(activePage) {
    const user = getUser();
    const role = user?.role || 'student';

    // 老师可以访问所有页面
    const teacherPages = [
        { id: 'dashboard', icon: 'bi-speedometer2', name: '仪表盘', url: 'dashboard.html' },
        { id: 'students', icon: 'bi-people', name: '学生管理', url: 'students.html' },
        { id: 'courses', icon: 'bi-book', name: '课程管理', url: 'courses.html' },
        { id: 'selections', icon: 'bi-card-checklist', name: '选课管理', url: 'selections.html' }
    ];

    // 学生只能查看自己的选课
    const studentPages = [
        { id: 'dashboard', icon: 'bi-speedometer2', name: '仪表盘', url: 'dashboard.html' },
        { id: 'courses', icon: 'bi-book', name: '查看课程', url: 'courses.html' },
        { id: 'selections', icon: 'bi-card-checklist', name: '我的选课', url: 'selections.html' }
    ];

    const pages = role === 'teacher' ? teacherPages : studentPages;

    let navLinks = pages.map(p => `
        <a href="${p.url}" class="nav-link ${activePage === p.id ? 'active' : ''}">
            <i class="bi ${p.icon}"></i>${p.name}
        </a>
    `).join('');

    const roleLabel = role === 'teacher' ? '教师' : '学生';
    const roleBadge = role === 'teacher' ? 'bg-warning text-dark' : 'bg-info';

    return `
        <nav class="sidebar">
            <div class="sidebar-header">
                <h4><i class="bi bi-mortarboard me-2"></i>学生信息管理</h4>
                <small class="text-muted">${user?.name || ''} <span class="badge ${roleBadge}">${roleLabel}</span></small>
            </div>
            <div class="sidebar-nav">
                ${navLinks}
            </div>
            <div class="sidebar-footer">
                <button class="btn btn-outline-light btn-sm" onclick="logout()">
                    <i class="bi bi-box-arrow-left me-1"></i>退出登录
                </button>
            </div>
        </nav>
    `;
}

// Toast 容器 HTML
function getToastContainerHTML() {
    return '<div id="toastContainer" class="toast-container"></div>';
}
