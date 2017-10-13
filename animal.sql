DROP DATABASE animal;
CREATE DATABASE animal;
USE animal;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for features
-- ----------------------------
DROP TABLE IF EXISTS `features`;
CREATE TABLE `features`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `feature` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of features
-- ----------------------------
INSERT INTO `features` VALUES (1, '有毛发');
INSERT INTO `features` VALUES (2, '能产乳');
INSERT INTO `features` VALUES (3, '有羽毛');
INSERT INTO `features` VALUES (4, '能飞行');
INSERT INTO `features` VALUES (5, '能生蛋');
INSERT INTO `features` VALUES (6, '能吃肉');
INSERT INTO `features` VALUES (7, '长有爪子');
INSERT INTO `features` VALUES (8, '长有利齿');
INSERT INTO `features` VALUES (9, '眼睛前视');
INSERT INTO `features` VALUES (10, '长有蹄');
INSERT INTO `features` VALUES (11, '反刍');
INSERT INTO `features` VALUES (12, '颜色是黄褐色');
INSERT INTO `features` VALUES (13, '有深色的斑点');
INSERT INTO `features` VALUES (14, '有黑色条纹');
INSERT INTO `features` VALUES (15, '有长腿');
INSERT INTO `features` VALUES (16, '有长颈');
INSERT INTO `features` VALUES (17, '颜色是白色');
INSERT INTO `features` VALUES (18, '不会飞');
INSERT INTO `features` VALUES (19, '颜色黑白相杂');
INSERT INTO `features` VALUES (20, '不能飞行');
INSERT INTO `features` VALUES (21, '能游水');
INSERT INTO `features` VALUES (22, '颜色是黑色和白色');
INSERT INTO `features` VALUES (23, '善于飞行');
INSERT INTO `features` VALUES (24, '哺乳动物');
INSERT INTO `features` VALUES (25, '鸟类动物');
INSERT INTO `features` VALUES (26, '食肉动物');
INSERT INTO `features` VALUES (27, '有蹄动物');
INSERT INTO `features` VALUES (28, '猎豹');
INSERT INTO `features` VALUES (29, '老虎');
INSERT INTO `features` VALUES (30, '长颈鹿');
INSERT INTO `features` VALUES (31, '斑马');
INSERT INTO `features` VALUES (32, '鸵鸟');
INSERT INTO `features` VALUES (33, '企鹅');
INSERT INTO `features` VALUES (34, '海燕');

-- ----------------------------
-- Table structure for rules
-- ----------------------------
DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conditions` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `result` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `can_end` int(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rules
-- ----------------------------
INSERT INTO `rules` VALUES (1, '有毛发', '哺乳动物', 0);
INSERT INTO `rules` VALUES (2, '能产乳', '哺乳动物', 0);
INSERT INTO `rules` VALUES (3, '有羽毛', '鸟类动物', 0);
INSERT INTO `rules` VALUES (4, '能飞行+能生蛋', '鸟类动物', 0);
INSERT INTO `rules` VALUES (5, '哺乳动物+吃肉', '食肉动物', 0);
INSERT INTO `rules` VALUES (6, '哺乳动物+长有爪子+长有利齿+眼睛前视', '食肉动物', 0);
INSERT INTO `rules` VALUES (7, '哺乳动物+长有蹄', '有蹄动物', 0);
INSERT INTO `rules` VALUES (8, '哺乳动物+反刍', '有蹄动物', 0);
INSERT INTO `rules` VALUES (9, '食肉动物+颜色是黄褐色+有深色的斑点', '猎豹', 1);
INSERT INTO `rules` VALUES (10, '食肉动物+颜色是黄褐色+有黑色条纹', '老虎', 1);
INSERT INTO `rules` VALUES (11, '有蹄动物+有长腿+有长颈+颜色是黄褐色+有深色的斑点', '长颈鹿', 1);
INSERT INTO `rules` VALUES (12, '有蹄动物+颜色是白色+有黑色条纹', '斑马', 1);
INSERT INTO `rules` VALUES (13, '鸟类动物+不会飞+有长腿+有长颈+颜色黑白相杂', '鸵鸟', 1);
INSERT INTO `rules` VALUES (14, '鸟类动物+不能飞行+能游泳+颜色是黑色和白色', '企鹅', 1);
INSERT INTO `rules` VALUES (15, '鸟类动物+善于飞行', '海燕', 1);

SET FOREIGN_KEY_CHECKS = 1;
