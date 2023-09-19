import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';  
import Navbar from './Navbar'
import ProductList from './ProductList'
import Cart from './Cart';
import ProductDetail from './ProductDetails';


function App() {

  return (
    <>
    <Router>
    <Navbar></Navbar>
      <Routes>
        <Route  path='/' Component={ProductList}/>
        <Route path='/cart' Component={Cart}/>
        <Route path='/:productId' Component={ProductDetail}/>
      </Routes>
    </Router>
    </>
  )
}

export default App
