document.addEventListener("DOMContentLoaded", () => {
  console.log("登录页面加载完成");
  
  const loginForm = document.getElementById("loginForm")
  const errorMessage = document.getElementById("errorMessage")

  if (!loginForm) {
    console.error("找不到登录表单元素");
    return;
  }

  loginForm.addEventListener("submit", (e) => {
    console.log("登录表单提交");
    e.preventDefault()

    const username = document.getElementById("username").value
    const password = document.getElementById("password").value

    console.log("用户名:", username);
    console.log("密码:", password ? "已输入" : "未输入");

    // 简单验证
    if (!username || !password) {
      errorMessage.textContent = "请输入用户名和密码";
      errorMessage.style.display = "block";
      console.log("验证失败：用户名或密码为空");
      return;
    }

    // 登录成功
    errorMessage.style.display = "none"
    console.log("登录验证通过，设置Cookie");

    // 设置Cookie (有效期为1天)
    const expirationDate = new Date()
    expirationDate.setDate(expirationDate.getDate() + 1)
    
    // 为file协议优化Cookie设置
    const cookieString = `username=${username}; expires=${expirationDate.toUTCString()}`;
    document.cookie = cookieString;
    
    // 额外使用localStorage作为备用存储
    localStorage.setItem('username', username);
    localStorage.setItem('loginTime', new Date().getTime());
    
    console.log("Cookie设置:", cookieString);
    console.log("LocalStorage备用存储已设置");
    console.log("准备跳转到首页");

    // 跳转到首页
    try {
      window.location.href = "index.html"
    } catch (error) {
      console.error("页面跳转失败:", error);
    }
  })
})

