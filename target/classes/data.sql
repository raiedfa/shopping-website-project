
CREATE TABLE users (
--    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) DEFAULT 'USER'
);

CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    image VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL
);


CREATE TABLE favorite_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    users_username VARCHAR(255),
    item_id INT,
    UNIQUE(users_username, item_id),
    FOREIGN KEY (users_username) REFERENCES users(username) ,
    FOREIGN KEY (item_id) REFERENCES items(id)
);


CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    users_username VARCHAR NOT NULL,
    creation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    shipping_address VARCHAR(255) NOT NULL,
    total_price DOUBLE NOT NULL,
    status VARCHAR(10) NOT NULL,
    FOREIGN KEY (users_username) REFERENCES users(username)
);


CREATE TABLE order_items (
   order_id INT,
    item_id INT,
    quantity INT,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES orders(id) ,
    FOREIGN KEY (item_id) REFERENCES items(id)
);





INSERT INTO users (first_name, last_name, email, phone, address, username, password, role) VALUES
('Raied', 'Falah', 'raied@gmail.com', '0509244236', 'kfar sumia', 'raiedfalah', '$2a$10$24P9JHWZJm8yRsJRpP4a.e11OvU9ynMvz6XAKJOrxl8Nhph7mojJ2', 'USER');
--
--
--
INSERT INTO items (title, image, price, stock)
VALUES
('Wireless Mouse', 'https://www.bhphotovideo.com/images/images2500x2500/Microsoft_GMF_00010_Wireless_Mobile_Mouse_3500_745203.jpg', 25, 50),
('Mechanical Keyboard', 'https://cdn.shopify.com/s/files/1/0059/0630/1017/t/5/assets/keychronv3custommechanicalkeyboardwithqmkvia-1665383938607.jpg?v=1665383941', 60, 30),
('HD Screen', 'https://1.bp.blogspot.com/-Od-fz4UDYUo/UwnOpmIxVaI/AAAAAAAAAGU/kLxXHJ0SBQ8/s1600/HANNSPREE+HANNS.G+16-INCH+HL163ABB+HD+Widescreen+LED+Monitor.jpg', 200, 20),
('USB-C Hub', 'https://sm.pcmag.com/t/pcmag_au/photo/e/euasoo-9-i/euasoo-9-in-1-usb-c-hub_aqex.1024.jpg', 45, 40),
('Bluetooth Speaker', 'https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6356/6356535_sd.jpg', 60, 25),
('Gaming Headset', 'https://th.bing.com/th/id/R.9adb2bb3a7064665f34dd9672d558d68?rik=DwWn295OU1kczQ&pid=ImgRaw&r=0', 90, 15),
('Webcam 1080p', 'https://m.media-amazon.com/images/I/71c6VcE1DbL._AC_SL1500_.jpg', 50, 35),
('Laptop ', 'https://http2.mlstatic.com/D_NQ_NP_743125-MLU73999131540_012024-O.webp', 75, 10),
('Iphone 14', 'https://tse1.mm.bing.net/th/id/OIP.SfmPBsJpiVWaJDoB7KrOsQHaFO?r=0&rs=1&pid=ImgDetMain&cb=idpwebpc1', 900, 18),
('airpods pro', 'https://tse3.mm.bing.net/th/id/OIP.mS7-1SV-xpBxttAl50S8AgHaGn?r=0&rs=1&pid=ImgDetMain&cb=idpwebpc1', 120, 60);



INSERT INTO orders (users_username, creation_date, shipping_address, total_price, status)
VALUES ('raiedfalah', CURRENT_DATE, 'kisra sumia', 150, 'TEMP');












