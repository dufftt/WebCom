import { Request, Response } from "express";
import jwt from 'jsonwebtoken';
import { jwtVerify, SignJWT } from 'jose';

var express = require("express");
var cors = require('cors');
var app = express();
const PORT = 3000;
const secret = 'ewwvuinviiIUENVQInfoNEIWNWEnucinwP';
const joseSecret = new TextEncoder().encode(secret);
const corsOptions = {
  origin: '*', // Only allow this origin
  //credentials: true, // Allow cookies, authorization headers, etc. if needed
  optionsSuccessStatus: 204 // Handle preflight requests
};


export function setNewToken(payload: any){
 const token = jwt.sign(payload, secret, {
  expiresIn: '15m'
});
    //console.log(token)
       return token;
}

export function verifyToken(req: any) {
     const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];
if (!token) {
    return false
  }

  try {
    // Verify token using secret key
    const decoded = jwt.verify(token, secret);
    return true;
    //req.user = decoded; // Add user data to request object
    // next(); // Move to the next middleware/route handler
  } catch (err) {
    return false
  }
}

app.use(express.json());
app.use(cors(corsOptions));
// app.use(function(req, res, next) {
//     res.header("Access-Control-Allow-Origin", "*");
//     res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//     next();
// });


interface Book {
id: number;
title: string;
}

interface productItem{
   productId: number,
    productName: String,
    productDescription: String,
    productPrice: number
    productImageUrl: String[]
}

const products: productItem[] = [{ productId: 1, productName: 'iPhone', productPrice: 949.98, productImageUrl: ['/iphone.jpg'], productDescription: 'A mobile Phone by Apple' },
    { productId: 2, productName: 'HP Laptop', productPrice: 899.95, productImageUrl: ['/laptop.jpg'], productDescription: 'Laptop by HP' },
    { productId: 3, productName: 'Powerbeats', productPrice: 199.99, productImageUrl: ['/headphone.jpg'], productDescription: 'PowerBank with ultra fast speed' },]

    interface Customer{
      customerId: number,
      name: String,
      email: String,
      MobNumber: number,
      password: String
    }

    const customers: Customer[] = [{
      customerId: 1,
      name: "Sourav",
      email: "souravsharma@gmail.com",
      MobNumber: 1234567890,
      password: "sour@1234"
    },
  {
    customerId: 2,
    name: "Jack",
    email: "jack@gmail.com",
    MobNumber: 1234567890,
    password: "jacky"
  }]
// app.get('/api/getProducts', (req: Request, resp: Response) => {
//    if(verifyToken(req)){
//      resp.status(200).json(books);
//     console.log(books);
//    }else{
//     resp.sendStatus(404);
//    }
   
// }) ;

app.get('/api/getProducts', (req: Request, resp: Response) => {
  resp.status(200).json(products);
    console.log(products);
   
}) ;

app.get('/api/getProductById', (req: Request, resp: Response) => {
  const {productId} = req.query;
  const product = products.find(prod => prod.productId.toString() === productId);
  resp.status(200).json(product);
    console.log(product);
   
}) ;

// app.post('/api/updateBooks', (req: Request, resp: Response) => {
//     console.log("got new book");
//     const newBook: Book = req.body;
//     console.log(newBook);
//     books.push(newBook);
//     resp.status(201).json(newBook);
// });

app.post('/login',(req: any, resp: any) =>{
    //console.log(req)
    const payload = ({email: req.body.email,password: req.body.password})
    console.log("username:"+payload.email+" password: "+payload.password)
    
    const validCustomer = customers.find(cust => cust.email===payload.email && cust.password === payload.password)
    console.log(validCustomer)
    if(validCustomer!==undefined){
     resp.json({token: setNewToken(payload),
                    name: validCustomer.name,
                    customerId: validCustomer.customerId,
                    email: validCustomer.email
        })
    }
    // if(payload.email==='sourav' && payload.password==='sour@1234'){
    //     resp.json({token: setNewToken(payload),
    //                 name: "Sourav Sharma",
    //                 customerId: "1456"
    //     })
    // }
    else{
        resp.json({message:'Invalid Id/Password'})
    }
    
})

app.post('/register',(req: any, resp: any) =>{
  //console.log(req)
  const customer = ({customerId: customers[customers.length-1].customerId +1,
      name: req.body.name,
      email: req.body.email,
      MobNumber: req.body.mobNumber,
      password: req.body.Password})
      console.log(customer)
  customers.push(customer)
  const payload = ({email: req.body.email,password: req.body.password})
  resp.json({token: setNewToken(payload),
                    name: customer.name,
                    customerId: customer.customerId,
                    email: customer.email
       

})
})


app.listen(PORT, () =>{
    console.log(`App running on server ${PORT}`);
})