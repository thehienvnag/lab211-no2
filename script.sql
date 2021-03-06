USE [BookStoreDB]
GO
/****** Object:  Table [dbo].[Bills]    Script Date: 2/8/2020 4:12:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bills](
	[ImportDate] [datetime] NOT NULL,
	[UserID] [varchar](50) NOT NULL,
	[BillCode] [varchar](30) NOT NULL,
 CONSTRAINT [PK_Bills] PRIMARY KEY CLUSTERED 
(
	[BillCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 2/8/2020 4:12:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Books](
	[BookID] [varchar](30) NOT NULL,
	[BookTitle] [nvarchar](100) NOT NULL,
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Author] [nvarchar](100) NOT NULL,
	[Categories] [nvarchar](30) NOT NULL,
	[ImageName] [varchar](30) NOT NULL,
	[ImportDate] [datetime] NOT NULL,
	[Status] [varchar](10) NOT NULL,
 CONSTRAINT [PK_Books] PRIMARY KEY CLUSTERED 
(
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BookSale]    Script Date: 2/8/2020 4:12:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookSale](
	[BillCode] [varchar](30) NOT NULL,
	[BookID] [varchar](30) NOT NULL,
	[Amount] [int] NOT NULL,
 CONSTRAINT [PK_BookSale] PRIMARY KEY CLUSTERED 
(
	[BillCode] ASC,
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discounts]    Script Date: 2/8/2020 4:12:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discounts](
	[DiscountCode] [varchar](20) NOT NULL,
	[DiscountPercent] [float] NOT NULL,
	[ExpiredDate] [datetime] NOT NULL,
	[UserID] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Discounts] PRIMARY KEY CLUSTERED 
(
	[DiscountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 2/8/2020 4:12:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[UserID] [varchar](50) NOT NULL,
	[Username] [nvarchar](60) NOT NULL,
	[Password] [varchar](65) NOT NULL,
	[Phone] [varchar](10) NULL,
	[Address] [nvarchar](150) NULL,
	[Email] [varchar](90) NOT NULL,
	[Role] [varchar](10) NOT NULL,
	[Status] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Books] ([BookID], [BookTitle], [Price], [Quantity], [Author], [Categories], [ImageName], [ImportDate], [Status]) VALUES (N'b123', N'Nha Gia Kim', 32000, 4, N'The Hien', N'Thriller', N'hien.png', CAST(N'2020-01-29T10:13:00.897' AS DateTime), N'Active')
INSERT [dbo].[Registration] ([UserID], [Username], [Password], [Phone], [Address], [Email], [Role], [Status]) VALUES (N'khoa', N'khoa huynh', N'3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', N'0933245643', N'Binh Khanh', N'khoa1234@gmail.com', N'USER', N'Active')
INSERT [dbo].[Registration] ([UserID], [Username], [Password], [Phone], [Address], [Email], [Role], [Status]) VALUES (N'nhu', N'Nguyen Ngoc Quynh Nhu', N'3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', N'0875267993', N'Vinh Long', N'nhu123@gmail.com', N'USER', N'Active')
INSERT [dbo].[Registration] ([UserID], [Username], [Password], [Phone], [Address], [Email], [Role], [Status]) VALUES (N'quang', N'Nhat Quang', N'3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', N'0988234223', N'Long Xuyen', N'quangkel@gmail.com', N'USER', N'Active')
INSERT [dbo].[Registration] ([UserID], [Username], [Password], [Phone], [Address], [Email], [Role], [Status]) VALUES (N'quynhtrang', N'Cao Quynh Trang', N'3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', N'0918076888', N'Nam Dong', N'trang@gmail.com', N'ADMIN', N'Active')
INSERT [dbo].[Registration] ([UserID], [Username], [Password], [Phone], [Address], [Email], [Role], [Status]) VALUES (N'thehien', N'Huynh The Hien', N'3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', N'0918076861', N'Phan Ba Vanh', N'thehien@gmail.com', N'USER', N'Active')
INSERT [dbo].[Registration] ([UserID], [Username], [Password], [Phone], [Address], [Email], [Role], [Status]) VALUES (N'thehiendev', N'Huynh The Hien', N'3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', N'0911887345', N'My Binh', N'thehien@gmail.com', N'USER', N'Active')
ALTER TABLE [dbo].[Bills]  WITH CHECK ADD  CONSTRAINT [FK_BookSale_Registration] FOREIGN KEY([UserID])
REFERENCES [dbo].[Registration] ([UserID])
GO
ALTER TABLE [dbo].[Bills] CHECK CONSTRAINT [FK_BookSale_Registration]
GO
ALTER TABLE [dbo].[BookSale]  WITH CHECK ADD  CONSTRAINT [FK_BookSale_Bills] FOREIGN KEY([BillCode])
REFERENCES [dbo].[Bills] ([BillCode])
GO
ALTER TABLE [dbo].[BookSale] CHECK CONSTRAINT [FK_BookSale_Bills]
GO
ALTER TABLE [dbo].[BookSale]  WITH CHECK ADD  CONSTRAINT [FK_BookSale_Books] FOREIGN KEY([BookID])
REFERENCES [dbo].[Books] ([BookID])
GO
ALTER TABLE [dbo].[BookSale] CHECK CONSTRAINT [FK_BookSale_Books]
GO
ALTER TABLE [dbo].[Discounts]  WITH CHECK ADD  CONSTRAINT [FK_Discounts_Registration] FOREIGN KEY([UserID])
REFERENCES [dbo].[Registration] ([UserID])
GO
ALTER TABLE [dbo].[Discounts] CHECK CONSTRAINT [FK_Discounts_Registration]
GO
