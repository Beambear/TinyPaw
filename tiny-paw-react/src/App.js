import logo from './logo.svg';
import './App.css';
import Home from './pages/home';
import NavBar from './components/navigation-bar/NavBar';
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <NavBar/>
      </header>
      <Home/>
    </div>
  );
}

export default App;
