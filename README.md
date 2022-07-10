#  Spring Data R2DBC CRUD

This is a Spring WebFlux sample application which uses Postgres DB + R2DBC for demonstrating CRUD operations.


| Method |       Request       |                                          Description |
|--------|:-------------------:|-----------------------------------------------------:|
| GET    |     product/all     |                           list all recorded products |
| GET    | product/{productId} |                                    get product by ID |
| POST   |       product       |      save new product(ID is assigned automatically ) |
| PUT    | product/{productId} |                                 update product by ID |
| POST   |    product/save     |                                     save new product |
| DELETE | product/{productId} |                                 delete product by ID |

