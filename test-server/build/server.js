"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.setNewToken = setNewToken;
exports.verifyToken = verifyToken;
const jsonwebtoken_1 = __importDefault(require("jsonwebtoken"));
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
function setNewToken(payload) {
    const token = jsonwebtoken_1.default.sign(payload, secret, {
        expiresIn: '15m'
    });
    //console.log(token)
    return token;
}
function verifyToken(req) {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];
    if (!token) {
        return false;
    }
    try {
        // Verify token using secret key
        const decoded = jsonwebtoken_1.default.verify(token, secret);
        return true;
        //req.user = decoded; // Add user data to request object
        // next(); // Move to the next middleware/route handler
    }
    catch (err) {
        return false;
    }
}
app.use(express.json());
app.use(cors(corsOptions));
const products = [{ productId: 1, productName: 'iPhone', productPrice: 949.98, productImageUrl: ['/iphone.jpg'], productDescription: 'A mobile Phone by Apple' },
    { productId: 2, productName: 'HP Laptop', productPrice: 899.95, productImageUrl: ['/laptop.jpg'], productDescription: 'Laptop by HP' },
    { productId: 3, productName: 'Powerbeats', productPrice: 199.99, productImageUrl: ['/headphone.jpg'], productDescription: 'PowerBank with ultra fast speed' },];
const customers = [{
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
    }];
// app.get('/api/getProducts', (req: Request, resp: Response) => {
//    if(verifyToken(req)){
//      resp.status(200).json(books);
//     console.log(books);
//    }else{
//     resp.sendStatus(404);
//    }
// }) ;
app.get('/api/getProducts', (req, resp) => {
    resp.status(200).json(products);
    console.log(products);
});
app.get('/api/getProductById', (req, resp) => {
    const { productId } = req.query;
    const product = products.find(prod => prod.productId.toString() === productId);
    resp.status(200).json(product);
    console.log(product);
});
// app.post('/api/updateBooks', (req: Request, resp: Response) => {
//     console.log("got new book");
//     const newBook: Book = req.body;
//     console.log(newBook);
//     books.push(newBook);
//     resp.status(201).json(newBook);
// });
app.post('/login', (req, resp) => {
    //console.log(req)
    const payload = ({ email: req.body.email, password: req.body.password });
    console.log("username:" + payload.email + " password: " + payload.password);
    const validCustomer = customers.find(cust => cust.email === payload.email && cust.password === payload.password);
    console.log(validCustomer);
    if (validCustomer !== undefined) {
        resp.json({ token: setNewToken(payload),
            name: validCustomer.name,
            customerId: validCustomer.customerId,
            email: validCustomer.email
        });
    }
    // if(payload.email==='sourav' && payload.password==='sour@1234'){
    //     resp.json({token: setNewToken(payload),
    //                 name: "Sourav Sharma",
    //                 customerId: "1456"
    //     })
    // }
    else {
        resp.json({ message: 'Invalid Id/Password' });
    }
});
app.post('/register', (req, resp) => {
    //console.log(req)
    const customer = ({ customerId: customers[customers.length - 1].customerId + 1,
        name: req.body.name,
        email: req.body.email,
        MobNumber: req.body.mobNumber,
        password: req.body.Password });
    console.log(customer);
    customers.push(customer);
    const payload = ({ email: req.body.email, password: req.body.password });
    resp.json({ token: setNewToken(payload),
        name: customer.name,
        customerId: customer.customerId,
        email: customer.email
    });
});
app.listen(PORT, () => {
    console.log(`App running on server ${PORT}`);
});
//# sourceMappingURL=server.js.map