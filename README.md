# MockwareHouse

A simple warehouse management web API with basic inventory handling features.

## Prerequisites

- Requires a running PostgreSQL installation. Configure connection information in  
  `src/main/resources/application.yml`.

- Create the database schema using `src/main/postgres_init/dbinit.sql`.

## Usage

### Start the server

In the project directory:

```bash
./mvnw spring-boot:run
```

The API will be available at `localhost:8080`.

---

### Endpoints with Usage Examples
All return values are in JSON. Endpoints are also available at
`localhost:8080/swagger-ui/index.html`.

#### `/api/warehouses`

- **GET**: Returns all warehouse records.

```bash
curl http://localhost:8080/api/warehouses
```

- **POST**: Creates a warehouse record and returns it. Requires a warehouse in request body.

```bash
curl -X POST "http://localhost:8080/api/warehouses" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Main Warehouse",
    "address": "123 Storage St",
    "capacity": "10000",
    "managerName": "John Doe"
  }'
```

---

#### `/api/warehouses/{id}`
(All return `notFound` if the record doesn't exist)

- **GET**: Returns the warehouse record with the given ID.

```bash
curl http://localhost:8080/api/warehouses/1
```

- **PUT**: Updates the selected warehouse record and returns it. Requires a warehouse in request body.

```bash
curl -X PUT "http://localhost:8080/api/warehouses/1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Warehouse",
    "address": "456 Updated St",
    "capacity": "15000",
    "managerName": "Jane Smith"
  }'
```

- **DELETE**: Deletes the selected warehouse record and returns `noContent`.

```bash
curl -X DELETE http://localhost:8080/api/warehouses/1
```

---

#### `/api/warehouses/search`

- **GET**: Returns specific warehouse records based on search criteria.

```bash
curl "http://localhost:8080/api/warehouses/search?name=Main"
curl "http://localhost:8080/api/warehouses/search?minCapacity=5000"
```

---

#### `/api/warehouses/{warehouseId}/inventory`

- **GET**: Returns the inventory records of the given warehouse or `notFound` if the warehouse doesn't exist.

```bash
curl http://localhost:8080/api/warehouses/1/inventory
```

---

#### `/api/warehouses/{warehouseId}/inventory/product`

- **GET**: Returns the inventory records of the given warehouse containing the given product. Requires a `productId` request parameter.

```bash
curl "http://localhost:8080/api/warehouses/1/inventory/product?productId=101"
```

---

#### `/api/inventories`

- **GET**: Returns all inventory records.

```bash
curl http://localhost:8080/api/inventories
```

- **POST**: Creates an inventory record and returns it. Requires an inventory in request body.

```bash
curl -X POST "http://localhost:8080/api/inventories" \
  -H "Content-Type: application/json" \
  -d '{
    "warehouse": {"id": "1"},
    "product": {"id": "1"},
    "quantity": "200",
    "minStock": "50",
    "maxStock": "100"
  }'
```

---

#### `/api/inventories/{id}`
(All return `notFound` if the record doesn't exist)

- **GET**: Returns the inventory record with the given ID.

```bash
curl http://localhost:8080/api/inventories/1
```

- **PUT**: Updates the selected inventory record and returns it.

```bash
curl -X PUT "http://localhost:8080/api/inventories/1" \
  -H "Content-Type: application/json" \
  -d '{
    "quantity": "250",
    "minStock": "60",
    "maxStock": "80"
  }'
```

- **DELETE**: Deletes the selected inventory record and returns `noContent`.

```bash
curl -X DELETE http://localhost:8080/api/inventories/1
```

---

#### `/api/inventories/product`

- **GET**: Returns the inventory records containing the given product. Requires a `productId` request parameter.

```bash
curl "http://localhost:8080/api/inventories/product?productId=101"
```

---

#### `/api/inventories/lowstock`

- **GET**: Returns the inventory records where `quantity < minStock`.

```bash
curl http://localhost:8080/api/inventories/lowstock
```

---

#### `/api/products`

- **GET**: Returns all product records.

```bash
curl http://localhost:8080/api/products
```

- **POST**: Creates a product record and returns it. Requires a product in request body.

```bash
curl -X POST "http://localhost:8080/api/products" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Gadget",
    "description": "Very Useful Gadget",
    "category": "Electronics",
    "price": "49.99"
    "weight": "1.5"
  }'
```

---

#### `/api/products/{id}`
(All return `notFound` if the record doesn't exist)

- **GET**: Returns the product record with the given ID.

```bash
curl http://localhost:8080/api/products/1
```

- **PUT**: Updates the selected product record and returns it.

```bash
curl -X PUT "http://localhost:8080/api/products/1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Gadget",
    "description": "Even More Useful Gadget",
    "category": "Electronics",
    "price": "59.99"
    "weight": "2"
  }'
```

- **DELETE**: Deletes the selected product record and returns `noContent`.

```bash
curl -X DELETE http://localhost:8080/api/products/1
```

---

#### `/api/products/search`

- **GET**: Returns product records based on search criteria.  
  Requires request parameters: either `category`, `name`, or both `minPrice` and `maxPrice`.

```bash
curl "http://localhost:8080/api/products/search?category=Electronics"
curl "http://localhost:8080/api/products/search?name=Gadget"
curl "http://localhost:8080/api/products/search?minPrice=10&maxPrice=100"
```
