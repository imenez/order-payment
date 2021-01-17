# order-payment
order-payment postgre spring boot microservices


request for adding order
{
    "order":{
            "id":"test3",
            "name":"order3",
            "qty": 1,
            "price": 20
            },
    "payment":{}
}

http://localhost:8090/orders/addOrder
