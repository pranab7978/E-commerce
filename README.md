🛒 ShopEase - Modern E-Commerce Platform
ShopEase is a premium, full-stack e-commerce application featuring a futuristic "Cyber-Dark" user interface. 
The project is built using a decoupled architecture, where the frontend is managed in VS Code and the backend is powered by Spring Boot in Eclipse.
Features
Premium Landing Page: Smooth animations and a high-end aesthetic.

Dynamic Product Catalog: Filter products by category (Electronics, Fashion, Fitness) and gender.

Quick View Modal: Multi-angle image gallery for every product.

Admin Dashboard: Add new inventory directly to the database.

Secure Access: Clean Login and Registration forms.

Responsive Cart: Fully designed shopping cart and order summary.

🛠️ Tech Stack
Frontend (VS Code)
HTML5 & CSS3: Custom modern UI with Glassmorphism and CSS variables.

JavaScript (ES6): Fetch API for backend communication and DOM manipulation for filtering.

Backend (Eclipse)
Java 17+

Spring Boot: REST API development.
ShopEase/
├── frontend/ (VS Code Project)
│   ├── index.html       # Landing Page
│   ├── products.html    # Product Listing & Filtering
│   ├── admin.html       # Inventory Management
│   ├── cart.html        # Checkout Page
│   └── login.html       # User Authentication
│
└── backend/ (Eclipse Project)
    ├── src/main/java/com/shopease/
    │   ├── Controller/  # API Endpoints
    │   ├── Model/       # Database Entities (User, Product)
    │   └── Repository/  # Database Queries
    └── src/main/resources/
        └── application.properties # Database Config
        

Spring Data JPA: Database ORM.

MySQL: Persistent data storage.
