import React from 'react';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Button from './Button';

const ProductDetail = ({ productsId }) => {
    const [products, setProducts] = useState(0)
    const { productId } = useParams();
    const fetchData = (productId) => {
        fetch(`http://localhost:8080/api/products/${productId}`)
          .then(response => {
            return response.json()
          })
          .then(data => {
            setProducts(data)
          })
      }

    useEffect(()=>{
        fetchData(productId)
    },[])

  if (!products) {
    return <div>Product not found</div>;
  }

  return (
    <div>
      <h1>Product Detail</h1>
      <h2>{products.name}</h2>
      <p>Price: ${products.price}</p>
      {/* Add more product details as needed */}
      
    </div>
  );
};

export default ProductDetail;