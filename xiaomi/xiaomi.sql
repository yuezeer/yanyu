/*
 Navicat Premium Data Transfer

 Source Server         : 328
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : xiaomi

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 21/04/2023 21:27:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uid` int(0) NOT NULL,
  `level` int(0) NOT NULL,
  `createtime` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_address_id`(`uid`) USING BTREE,
  CONSTRAINT `fk_address_id` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (8, '河南省', '洛阳市', '栾川县', '苏园路6号3楼南京艾瑞11', '徐龙飞', '13739242969', 2, 0, '2022-04-30');
INSERT INTO `tb_address` VALUES (9, '河南省', '洛阳市', '栾川县', '苏园路6号3楼南京艾瑞33', '黄清泉', '2323', 3, 0, '2022-04-30');
INSERT INTO `tb_address` VALUES (10, '上海市', '普陀区', '', '苏园路6号3楼南京艾瑞22', '王豪22', '11222', 4, 0, '2022-04-30');
INSERT INTO `tb_address` VALUES (13, '辽宁省', '锦州市', '北镇市', '幸福小区A东11单元', '张三', '11111', 5, 0, '2022-05-11');
INSERT INTO `tb_address` VALUES (15, '湖北省', '襄阳市', '谷城县', '幸福小区A东13单元', '张三', '11111', 6, 0, '2022-05-11');
INSERT INTO `tb_address` VALUES (16, '广东省', '佛山市', '高明区', '荣康园8栋101', '徐龙飞', '2323', 15, 0, '2022-05-24');
INSERT INTO `tb_address` VALUES (17, '黑龙江省', '鹤岗市', '绥滨县', 'xxx小区', '黄清泉', '112', 15, 0, '2022-05-24');
INSERT INTO `tb_address` VALUES (18, '江苏省', '泰州市', '高港区', '幸福小区22号', '张三', '113', 16, 0, '2022-05-27');
INSERT INTO `tb_address` VALUES (19, '上海市', '奉贤区', '', '幸福小区22单元', '小红', '112', 17, 0, '2022-06-10');
INSERT INTO `tb_address` VALUES (26, '河南省', '信阳市', '息县', '水岸花都', '陈靖文', '17629993903', 20, 0, '2023-01-01');
INSERT INTO `tb_address` VALUES (28, '河南省', '信阳市', '息县', '息县', '陈靖文', '17629993903', 21, 0, '2023-01-04');
INSERT INTO `tb_address` VALUES (29, '河南省', '信阳市', '息县', '水岸花都', '陈靖文', '17629993903', 20, 0, '2023-01-09');
INSERT INTO `tb_address` VALUES (30, '河南省', '洛阳市', '新安县', '1111', '111', '11111', 20, 0, '2023-04-15');
INSERT INTO `tb_address` VALUES (31, '河南省', '开封市', '尉氏县', '111', '111', '111', 20, 1, '2023-04-15');

-- ----------------------------
-- Table structure for tb_banner
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner`;
CREATE TABLE `tb_banner`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_banner
-- ----------------------------
INSERT INTO `tb_banner` VALUES (1, '/image/1.webp');
INSERT INTO `tb_banner` VALUES (2, '/image/2.webp');
INSERT INTO `tb_banner` VALUES (3, '/image/3.webp');
INSERT INTO `tb_banner` VALUES (4, '/image/4.webp');
INSERT INTO `tb_banner` VALUES (5, '/image/5.webp');

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart`  (
  `cid` int(0) NOT NULL AUTO_INCREMENT,
  `id` int(0) DEFAULT NULL,
  `pid` int(0) DEFAULT NULL,
  `num` int(0) DEFAULT NULL,
  `money` int(0) DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `fk_cart_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
INSERT INTO `tb_cart` VALUES (24, 15, 2, 2, 4000);
INSERT INTO `tb_cart` VALUES (25, 15, 21, 1, 2200);
INSERT INTO `tb_cart` VALUES (36, 15, 33, 1, 20);
INSERT INTO `tb_cart` VALUES (37, 16, 3, 3, 9000);
INSERT INTO `tb_cart` VALUES (38, 16, 6, 2, 6000);
INSERT INTO `tb_cart` VALUES (39, 17, 8, 3, 1368);
INSERT INTO `tb_cart` VALUES (40, 17, 15, 5, 5000);
INSERT INTO `tb_cart` VALUES (54, 21, 18, 1, 4000);
INSERT INTO `tb_cart` VALUES (55, 21, 29, 1, 100);
INSERT INTO `tb_cart` VALUES (59, 20, 4, 1, 2345);
INSERT INTO `tb_cart` VALUES (60, 20, 2, 1, 6000);

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pubdate` date DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` int(0) DEFAULT NULL,
  `star` tinyint(0) DEFAULT 0,
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `typeid` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_typeid`(`typeid`) USING BTREE,
  CONSTRAINT `fk_typeid` FOREIGN KEY (`typeid`) REFERENCES `tb_goods_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES (2, '黑鲨5 Pro', '2022-05-05', '/image/liebiao_xiaomint2.jpg', 6000, 4, '黑鲨5 Pro', 1);
INSERT INTO `tb_goods` VALUES (3, '黑鲨5', '2022-04-19', '/image/liebiao_xiaomimix.jpg', 4000, 5, '黑鲨5', 1);
INSERT INTO `tb_goods` VALUES (4, 'Redmi 10A', '2022-03-01', '/image/liebiao_hongmin4x.jpg', 2345, 6, 'Redmi 10A', 1);
INSERT INTO `tb_goods` VALUES (7, 'Redmi K40S', '2022-04-14', '/image/liebiao_xiaomi5s.jpg', 2000, 5, 'Redmi K40S', 1);
INSERT INTO `tb_goods` VALUES (8, 'Redmi K50 电竞版', '2022-04-24', '/image/pinpai2.png', 456, 6, 'Redmi K50 电竞版', 1);
INSERT INTO `tb_goods` VALUES (9, 'Xiaomi 11 青春活力版', '2022-05-05', '/image/pinpai3.png', 2000, 4, 'Xiaomi 11 青春活力版', 1);
INSERT INTO `tb_goods` VALUES (15, 'Redmi X55 2022款', '2022-04-01', '/image/pinpai4.png', 1000, 3, 'Redmi X55 2022款', 2);
INSERT INTO `tb_goods` VALUES (16, '小米电视6 55 OLED', '2022-04-15', '/image/6511d77270e94146c0b1f96b66d8cc58.webp', 2000, 3, '小米电视6 55 OLED', 2);
INSERT INTO `tb_goods` VALUES (17, 'Redmi X65 2022款', '2022-04-23', '/image/6511d77270e94146c0b1f96b66d8cc58.webp', 3000, 5, 'Redmi X65 2022款', 2);
INSERT INTO `tb_goods` VALUES (18, '小米拍拍4K高清投屏器', '2022-04-30', '/image/8871821795310769c1d3896c99b12381.webp', 4000, 6, '小米拍拍4K高清投屏器', 2);
INSERT INTO `tb_goods` VALUES (19, '小米电视 大师 77 OLED', '2022-04-22', '/image/pms_1593671513.90269727.jpg', 5000, 6, '小米电视 大师 77 OLED', 2);
INSERT INTO `tb_goods` VALUES (20, '小米电视6 至尊版 55英寸', '2022-04-16', '/image/932b583c6eccd8aabfa0771f8a854940.webp', 3200, 6, '小米电视6 至尊版 55英寸', 2);
INSERT INTO `tb_goods` VALUES (21, '小米电视 ES55 2022款', '2022-04-02', '/image/6511d77270e94146c0b1f958.webp', 2200, 4, '小米电视 ES55 2022款', 2);
INSERT INTO `tb_goods` VALUES (22, 'Xiaomi智能家庭屏 10', '2022-04-02', '/image/peijian6.png', 1000, 5, 'Xiaomi智能家庭屏 10', 6);

-- ----------------------------
-- Table structure for tb_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_type`;
CREATE TABLE `tb_goods_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_goods_type
-- ----------------------------
INSERT INTO `tb_goods_type` VALUES (1, '手机');
INSERT INTO `tb_goods_type` VALUES (2, '电视');
INSERT INTO `tb_goods_type` VALUES (3, '笔记本 平板');
INSERT INTO `tb_goods_type` VALUES (4, '家电');
INSERT INTO `tb_goods_type` VALUES (5, '出行 穿戴');
INSERT INTO `tb_goods_type` VALUES (6, '智能 路由器');
INSERT INTO `tb_goods_type` VALUES (7, '电源 配件');
INSERT INTO `tb_goods_type` VALUES (8, '耳机 音响');
INSERT INTO `tb_goods_type` VALUES (9, '生活 箱包');
INSERT INTO `tb_goods_type` VALUES (31, 'bb机');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` int(0) DEFAULT NULL,
  `money` int(0) DEFAULT NULL,
  `status` int(0) DEFAULT NULL COMMENT '1:未支付 2:支付失败 3:支付成功 4:发货 5、到货 6、评价',
  `time` datetime(0) DEFAULT NULL,
  `aid` int(0) DEFAULT NULL,
  `cids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_uid`(`uid`) USING BTREE,
  INDEX `fk_order_aid`(`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('050c89325337401fac8bfdbdc6335558', 15, 2000, 3, '2022-05-11 10:29:27', 8, '24');
INSERT INTO `tb_order` VALUES ('06c5103cb2644c7cb6ccf256acb3ff8e', 20, 3060, 1, '2023-01-01 07:39:28', 26, '52,53');
INSERT INTO `tb_order` VALUES ('100ad4a888f34d359c223060caad2f7f', 20, 3060, 1, '2023-01-03 01:27:56', 26, '52,53');
INSERT INTO `tb_order` VALUES ('1b9ae99a68ea45a0b0839304bb73ba48', 20, 3060, 1, '2023-01-03 08:52:12', 26, '52,53');
INSERT INTO `tb_order` VALUES ('1beaa44f989342d6bdf76ff1a7cd99fa', 20, 9000, 1, '2023-04-15 08:31:05', 30, '57,58');
INSERT INTO `tb_order` VALUES ('1bf00c8d6cef475fa8587bbb3b139cdd', 21, 4100, 1, '2023-01-04 04:27:16', 28, '54,55');
INSERT INTO `tb_order` VALUES ('22f918ea02f84c348a04e23e3a9a4af9', 20, 3060, 1, '2023-01-03 01:31:43', 26, '52,53');
INSERT INTO `tb_order` VALUES ('247e64cedf06425ca8604a9a334a6483', 20, 3360, 1, '2023-01-09 06:46:20', 26, '52,53,56');
INSERT INTO `tb_order` VALUES ('347eb4c566024ae6aefb06b37bfc8c0c', 20, 3060, 1, '2023-01-02 04:45:19', 26, '52,53');
INSERT INTO `tb_order` VALUES ('3493ba8493c744ec9e57e101d0070822', 20, 3060, 1, '2023-01-03 01:19:02', 26, '52,53');
INSERT INTO `tb_order` VALUES ('382f932ad223477894ccfa1d6ff0c96d', 15, 4000, 3, '2022-05-12 10:00:41', 8, '24');
INSERT INTO `tb_order` VALUES ('41e8927a8b68472e85232ae8e5952aa8', 20, 3060, 1, '2023-01-02 04:33:57', 26, '52,53');
INSERT INTO `tb_order` VALUES ('597d205e0a794951aabe58a9d4bd51a1', 20, 3060, 1, '2023-01-03 01:24:48', 26, '52,53');
INSERT INTO `tb_order` VALUES ('5a3bf6b3126b4979a403d411f95d5459', 20, 3060, 1, '2023-01-01 07:36:20', 26, '52,53');
INSERT INTO `tb_order` VALUES ('5dd571449076415e8a10bbbe0baf806a', 16, 15000, 1, '2022-05-27 08:11:15', 18, '37,38');
INSERT INTO `tb_order` VALUES ('5f6783b6ecd5420c81e24f9c10e6188e', 20, 3060, 1, '2023-01-01 07:39:38', 26, '52,53');
INSERT INTO `tb_order` VALUES ('6d8e4450425a4b19b1031a233f340906', 2, 2200, 1, '2022-04-11 10:20:21', 8, '25');
INSERT INTO `tb_order` VALUES ('7fb42bc296364b92b3e6919ab60726fb', 20, 3060, 1, '2023-01-01 07:27:57', 26, '52,53');
INSERT INTO `tb_order` VALUES ('8059479081b14e39a5f0ce90ffce170d', 3, 6200, 1, '2022-01-11 09:35:02', 10, '24,25');
INSERT INTO `tb_order` VALUES ('88fa2617ae7145329fb65c35a73861f8', 3, 5000, 3, '2022-07-11 10:16:44', 8, '25');
INSERT INTO `tb_order` VALUES ('9a80f8805d2440239d74eab71e876bf6', 20, 9000, 1, '2023-04-15 08:26:01', 30, '57,58');
INSERT INTO `tb_order` VALUES ('b330be82e7fd492cb8b004e926c22e6e', 15, 4000, 2, '2022-05-24 09:16:09', 16, '24');
INSERT INTO `tb_order` VALUES ('bd3e526f97fb480facb42a029f093597', 20, 3060, 1, '2023-01-03 08:52:31', 26, '52,53');
INSERT INTO `tb_order` VALUES ('bfa9456e05a847768e17ad093eaa9790', 20, 8345, 1, '2023-04-21 09:15:03', 26, '59,60');
INSERT INTO `tb_order` VALUES ('c44e96e573fd4d14a1b8150b471eb302', 17, 1368, 3, '2022-06-10 09:22:32', 19, '39');
INSERT INTO `tb_order` VALUES ('ccba417b8d0044578296f48d1a7834ce', 20, 3060, 1, '2023-01-01 07:34:41', 26, '52,53');
INSERT INTO `tb_order` VALUES ('cd465fe3ca9e475b9c322884abc982a5', 20, 8345, 1, '2023-04-15 08:34:39', 31, '59,60');
INSERT INTO `tb_order` VALUES ('eabe363f8ccf47d58cac529461b9b20d', 20, 3060, 1, '2023-01-02 04:33:39', 26, '52,53');
INSERT INTO `tb_order` VALUES ('f082657ec77849538952ad5023420ebf', 16, 6000, 3, '2022-05-27 08:14:07', 18, '38');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `flag` int(0) DEFAULT NULL,
  `role` int(0) DEFAULT NULL,
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'zhangsan', '01d7f40760960e7bd9443513f22ab9af', '2655904102@qq.com', '男', 1, 1, '');
INSERT INTO `tb_user` VALUES (2, 'lisi1', 'dc3a8f1670d65bea69b7b65048a0ac40', '2655904102@qq.com', '女', 1, 0, NULL);
INSERT INTO `tb_user` VALUES (3, 'lisi2', 'dc3a8f1670d65bea69b7b65048a0ac40', '2655904102@qq.com', '女', 1, 0, NULL);
INSERT INTO `tb_user` VALUES (4, 'lisi3', 'dc3a8f1670d65bea69b7b65048a0ac40', '2655904102@qq.com', '女', 1, 0, NULL);
INSERT INTO `tb_user` VALUES (5, 'lisi4', 'dc3a8f1670d65bea69b7b65048a0ac40', '2655904102@qq.com', '女', 1, 0, NULL);
INSERT INTO `tb_user` VALUES (6, 'lisi5', 'dc3a8f1670d65bea69b7b65048a0ac40', '2655904102@qq.com', '女', 1, 0, NULL);
INSERT INTO `tb_user` VALUES (15, 'wangwu', '9f001e4166cf26bfbdd3b4f67d9ef617', '2655904102@qq.com', '女', 1, 0, '6541');
INSERT INTO `tb_user` VALUES (16, 'xiaohong', '1167eac4687a0d8aae4d01efe9274cda', '2655904102@qq.com', '女', 1, 0, '8013');
INSERT INTO `tb_user` VALUES (17, 'xiaoxiao', '845d5f1153c27beed29f479640445148', '2655904102@qq.com', '女', 1, 0, '2865');
INSERT INTO `tb_user` VALUES (20, 'xiaochen', '5887a852598473f144d0accf88dc4d7c', '1565208389@qq.com', '男', 1, 0, NULL);
INSERT INTO `tb_user` VALUES (21, 'chenjing', '65ea34878ffd723ead9f196ecd7df7ca', '1565208389@qq.com', '男', 1, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
