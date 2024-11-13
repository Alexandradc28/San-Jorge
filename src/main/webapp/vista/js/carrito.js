const btnCart = document.querySelector('.container-cart-icon');
const containerCartProducts = document.querySelector('.container-cart-products');
btnCart.addEventListener('click', () => {
  containerCartProducts.classList.toggle('hidden-cart');
});

const cartInfo = document.querySelector('.cart-product');
const rowProduct = document.querySelector('.row-product');
const productsList = document.querySelector('#product-cards');
let allProducts = [];
const valorTotal = document.querySelector('.total-pagar');
const countProducts = document.querySelector('#contador-productos');
const cartEmpty = document.querySelector('.cart-empty');
const cartTotal = document.querySelector('.cart-total');

productsList.addEventListener('click', e => {
  if (e.target.classList.contains('btn-add-cart')) {
    const product = e.target.parentElement;
    const infoProduct = {
      quantity: 1,
      title: product.querySelector('h3').textContent,
      price: parseFloat(product.querySelector('h6').textContent.slice(2)), // Convertir el precio a float
    };
    const exits = allProducts.some(product => product.title === infoProduct.title);
    if (exits) {
      const products = allProducts.map(product => {
        if (product.title === infoProduct.title) {
          product.quantity++;
          return product;
        } else {
          return product;
        }
      });
      allProducts = [...products];
    } else {
      allProducts = [...allProducts, infoProduct];
    }
    showHTML();
  }
});

rowProduct.addEventListener('click', e => {
  if (e.target.classList.contains('bx-x-circle')) {
    const product = e.target.parentElement;
    const title = product.querySelector('h3').textContent;
    allProducts = allProducts.filter(product => product.title !== title);
    showHTML();
  }
});

const showHTML = () => {
  if (!allProducts.length) {
    cartEmpty.classList.remove('hidden');
    rowProduct.classList.add('hidden');
    cartTotal.classList.add('hidden');
  } else {
    cartEmpty.classList.add('hidden');
    rowProduct.classList.remove('hidden');
    cartTotal.classList.remove('hidden');
  }

  rowProduct.innerHTML = '';
  let total = 0;
  let totalOfProducts = 0;
  allProducts.forEach(product => {
    const containerProduct = document.createElement('div');
    containerProduct.classList.add('cart-product');
    containerProduct.innerHTML = `
      <div class="info-cart-product">
        <h6 class="cantidad-producto-carrito">${product.quantity}</h6>
        <h3 class="titulo-producto-carrito">${product.title}</h3>
        <h6 class="precio-producto-carrito">S/${product.price.toFixed(2)}</h6> <!-- Mostrar precio con decimales -->
      </div>
      <i class='bx bx-x-circle'></i>
    `;
    rowProduct.append(containerProduct);
    
    // Calcular el total en base a precios como float
    total += product.quantity * product.price;
    totalOfProducts += product.quantity;
  });

  // Mostrar total con decimales
  valorTotal.innerText = `Total: S/${total.toFixed(2)}`; // Mostrar total con decimales
  countProducts.innerText = totalOfProducts;

  // Guarda el carrito en el localStorage
  guardarEnLocalStorage();
};

const guardarEnLocalStorage = () => {
  localStorage.setItem('carrito', JSON.stringify(allProducts));
};

const recuperarDesdeLocalStorage = () => {
  const productosEnLocalStorage = JSON.parse(localStorage.getItem('carrito')) || [];
  allProducts = productosEnLocalStorage;
  showHTML();
};

window.addEventListener('load', recuperarDesdeLocalStorage);
