import './Home.css';
import Calculator from '../../components/calculator/Calculator';

const Home = () => {
    return (
        <div className="home">
            <Calculator/>
        </div> // 注意这里使用了正确的关闭标签
    );
}

export default Home;
