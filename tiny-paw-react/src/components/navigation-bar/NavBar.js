import "./NavBar.css"
const NavBar = () => {
    return(
        <div className = "navBar">
            <div className="logo"> TinyPaw</div>

            <div className = "navActions">
                <p className = "navItems"> 热量计算器</p>
                <p className = "navItems"> 登录</p>
                <p className = "navItems"> 注册</p>
            </div>
            
        </div>
    )
}

export default NavBar;