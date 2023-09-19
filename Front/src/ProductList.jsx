import { useState } from 'react'
import { useEffect } from 'react'
import "./styles/ProductList.css"
import { Link } from "react-router-dom";


function ProductList() {
  const [products, setProducts] = useState(0)

  const fetchData = () => {
    fetch("http://localhost:8080/api/products/")
      .then(response => {
        return response.json()
      })
      .then(data => {
        setProducts(data)
      })
  }

  useEffect(()=>{
    fetchData()
  },[])


  return (
    <>
      <h1>WELCOME TO JUANGUICOMMERCE</h1>
      {products.length > 0 &&(
        <ul>
          {
            products.map(product => (
              <li key={product.id}>
                <Link to={`/${product.id}`}>{product.name}</Link>
                <p>{product.price}</p>
                <p>{product.description}</p>
              </li>
            ))
          }
        </ul>
      )

      }
    </>
  )
}

export default ProductList