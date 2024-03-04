CREATE TABLE [dbo].[tblOrders] (
    orderID nvarchar(50) NOT NULL,
    userID nvarchar(50) NULL,
    orderDate DATE NULL,
    total DECIMAL(10, 2) NULL,
	fullName nvarchar(50) NULL,
	phoneNumber nvarchar(11) NULL,
	address nvarchar(255) NULL,
	email nvarchar(50) NULL,
	paymentMethod nvarchar(50) NULL,
    CONSTRAINT PK_tblOrders PRIMARY KEY (orderID),
    CONSTRAINT FK_OrderUser FOREIGN KEY (userID) REFERENCES tblUsers(userID)
);

CREATE TABLE [dbo].[tblProducts] (
    productID nvarchar(50) NOT NULL,
    name nvarchar(255) NULL,
    price DECIMAL(10, 2) NULL,
    quantity INT NULL,
	CONSTRAINT PK_tblProducts PRIMARY KEY (productID)
);

CREATE TABLE [dbo].[tblOrderDetail] (
    orderDetailID int IDENTITY(1,1) NOT NULL,
    productID nvarchar(50) NULL,
    orderID nvarchar(50) NULL,
    price DECIMAL(10, 2) NULL,
    quantity INT NULL,
	CONSTRAINT PK_tblOrderDetail PRIMARY KEY CLUSTERED (orderDetailID ASC) ON [PRIMARY],
    CONSTRAINT FK_ProductOrderDetail FOREIGN KEY (productID) REFERENCES [tblProducts](productID),
    CONSTRAINT FK_OrderIDOrderDetail FOREIGN KEY (orderID) REFERENCES [tblOrders](orderID)
);

INSERT INTO tblProducts (productID, name, price, quantity)
VALUES ('w001', 'glock', 200, 1000),
	   ('w002', 'Luu dan', 100, 1000),
	   ('w003', 'Xe tang', 1000, 1000),
	   ('w004', 'Dao phay', 50, 1000),
	   ('w005', 'May bay', 5000, 1000);
