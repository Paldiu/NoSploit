/*
 * Copyright 2016 NoSploit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.mafrans.nosploit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Data {

    public static List<ItemStack> books = new ArrayList();
    public static List<ItemStack> bookcd = new ArrayList();
    public static HashMap<Integer, Short> itemDataTable = new HashMap();
    public static List<EntityType> nonLivingEntities = new ArrayList();

    public static void registerData() {
        // Non Living Entities
        {
            nonLivingEntities.add(EntityType.PLAYER);
            nonLivingEntities.add(EntityType.AREA_EFFECT_CLOUD);
            nonLivingEntities.add(EntityType.ARMOR_STAND);
            nonLivingEntities.add(EntityType.ARROW);
            nonLivingEntities.add(EntityType.BOAT);
            nonLivingEntities.add(EntityType.COMPLEX_PART);
            nonLivingEntities.add(EntityType.DRAGON_FIREBALL);
            nonLivingEntities.add(EntityType.DROPPED_ITEM);
            nonLivingEntities.add(EntityType.EGG);
            nonLivingEntities.add(EntityType.ENDER_CRYSTAL);
            nonLivingEntities.add(EntityType.ENDER_PEARL);
            nonLivingEntities.add(EntityType.ENDER_SIGNAL);
            nonLivingEntities.add(EntityType.EXPERIENCE_ORB);
            nonLivingEntities.add(EntityType.FALLING_BLOCK);
            nonLivingEntities.add(EntityType.FIREBALL);
            nonLivingEntities.add(EntityType.FIREWORK);
            nonLivingEntities.add(EntityType.FISHING_HOOK);
            nonLivingEntities.add(EntityType.ITEM_FRAME);
            nonLivingEntities.add(EntityType.LEASH_HITCH);
            nonLivingEntities.add(EntityType.LIGHTNING);
            nonLivingEntities.add(EntityType.LINGERING_POTION);
            nonLivingEntities.add(EntityType.MINECART);
            nonLivingEntities.add(EntityType.MINECART_CHEST);
            nonLivingEntities.add(EntityType.MINECART_COMMAND);
            nonLivingEntities.add(EntityType.MINECART_FURNACE);
            nonLivingEntities.add(EntityType.MINECART_HOPPER);
            nonLivingEntities.add(EntityType.MINECART_MOB_SPAWNER);
            nonLivingEntities.add(EntityType.MINECART_TNT);
            nonLivingEntities.add(EntityType.PAINTING);
            nonLivingEntities.add(EntityType.PRIMED_TNT);
            nonLivingEntities.add(EntityType.SHULKER_BULLET);
            nonLivingEntities.add(EntityType.SMALL_FIREBALL);
            nonLivingEntities.add(EntityType.SNOWBALL);
            nonLivingEntities.add(EntityType.SPECTRAL_ARROW);
            nonLivingEntities.add(EntityType.SPLASH_POTION);
            nonLivingEntities.add(EntityType.THROWN_EXP_BOTTLE);
            nonLivingEntities.add(EntityType.TIPPED_ARROW);
            nonLivingEntities.add(EntityType.UNKNOWN);
            nonLivingEntities.add(EntityType.WEATHER);
            nonLivingEntities.add(EntityType.WITHER_SKULL);
        }

        // Item Data Table
        {
            // Blocks
            itemDataTable.put(1, (short) 6);
            itemDataTable.put(2, (short) 0);
            itemDataTable.put(3, (short) 2);
            itemDataTable.put(4, (short) 0);
            itemDataTable.put(5, (short) 5);
            itemDataTable.put(7, (short) 0);
            itemDataTable.put(12, (short) 1);
            itemDataTable.put(13, (short) 0);
            itemDataTable.put(14, (short) 0);
            itemDataTable.put(15, (short) 0);
            itemDataTable.put(16, (short) 0);
            itemDataTable.put(17, (short) 3);
            itemDataTable.put(19, (short) 1);
            itemDataTable.put(20, (short) 0);
            itemDataTable.put(21, (short) 0);
            itemDataTable.put(22, (short) 0);
            itemDataTable.put(24, (short) 2);
            itemDataTable.put(35, (short) 15);
            itemDataTable.put(41, (short) 0);
            itemDataTable.put(42, (short) 0);
            itemDataTable.put(44, (short) 7);
            itemDataTable.put(45, (short) 0);
            itemDataTable.put(47, (short) 0);
            itemDataTable.put(48, (short) 0);
            itemDataTable.put(49, (short) 0);
            itemDataTable.put(53, (short) 0);
            itemDataTable.put(56, (short) 0);
            itemDataTable.put(57, (short) 0);
            itemDataTable.put(67, (short) 0);
            itemDataTable.put(73, (short) 0);
            itemDataTable.put(79, (short) 0);
            itemDataTable.put(80, (short) 0);
            itemDataTable.put(82, (short) 0);
            itemDataTable.put(86, (short) 0);
            itemDataTable.put(87, (short) 0);
            itemDataTable.put(88, (short) 0);
            itemDataTable.put(89, (short) 0);
            itemDataTable.put(91, (short) 0);
            itemDataTable.put(95, (short) 15);
            itemDataTable.put(98, (short) 3);
            itemDataTable.put(103, (short) 0);
            itemDataTable.put(108, (short) 0);
            itemDataTable.put(109, (short) 0);
            itemDataTable.put(110, (short) 0);
            itemDataTable.put(112, (short) 0);
            itemDataTable.put(114, (short) 0);
            itemDataTable.put(121, (short) 0);
            itemDataTable.put(126, (short) 5);
            itemDataTable.put(128, (short) 0);
            itemDataTable.put(129, (short) 0);
            itemDataTable.put(133, (short) 0);
            itemDataTable.put(134, (short) 0);
            itemDataTable.put(135, (short) 0);
            itemDataTable.put(136, (short) 0);
            itemDataTable.put(139, (short) 1);
            itemDataTable.put(153, (short) 0);
            itemDataTable.put(155, (short) 2);
            itemDataTable.put(156, (short) 0);
            itemDataTable.put(159, (short) 15);
            itemDataTable.put(162, (short) 1);
            itemDataTable.put(163, (short) 0);
            itemDataTable.put(164, (short) 0);
            itemDataTable.put(168, (short) 2);
            itemDataTable.put(169, (short) 0);
            itemDataTable.put(170, (short) 0);
            itemDataTable.put(172, (short) 0);
            itemDataTable.put(173, (short) 0);
            itemDataTable.put(174, (short) 0);
            itemDataTable.put(179, (short) 2);
            itemDataTable.put(180, (short) 0);
            itemDataTable.put(182, (short) 0);
            itemDataTable.put(201, (short) 0);
            itemDataTable.put(202, (short) 0);
            itemDataTable.put(203, (short) 0);
            itemDataTable.put(205, (short) 0);
            itemDataTable.put(206, (short) 0);
            itemDataTable.put(213, (short) 0);
            itemDataTable.put(214, (short) 0);
            itemDataTable.put(215, (short) 0);
            itemDataTable.put(216, (short) 0);

            // Decorative
            itemDataTable.put(6, (short) 5);
            itemDataTable.put(18, (short) 3);
            itemDataTable.put(30, (short) 0);
            itemDataTable.put(31, (short) 2);
            itemDataTable.put(32, (short) 0);
            itemDataTable.put(37, (short) 0);
            itemDataTable.put(38, (short) 8);
            itemDataTable.put(39, (short) 0);
            itemDataTable.put(40, (short) 0);
            itemDataTable.put(50, (short) 15);
            itemDataTable.put(54, (short) 15);
            itemDataTable.put(58, (short) 0);
            itemDataTable.put(61, (short) 15);
            itemDataTable.put(65, (short) 15);
            itemDataTable.put(78, (short) 7);
            itemDataTable.put(81, (short) 0);
            itemDataTable.put(84, (short) 0);
            itemDataTable.put(85, (short) 0);
            itemDataTable.put(97, (short) 5);
            itemDataTable.put(101, (short) 0);
            itemDataTable.put(102, (short) 0);
            itemDataTable.put(106, (short) 0);
            itemDataTable.put(111, (short) 15);
            itemDataTable.put(113, (short) 0);
            itemDataTable.put(116, (short) 0);
            itemDataTable.put(120, (short) 15);
            itemDataTable.put(130, (short) 15);
            itemDataTable.put(145, (short) 11);
            itemDataTable.put(160, (short) 15);
            itemDataTable.put(161, (short) 1);
            itemDataTable.put(165, (short) 0);
            itemDataTable.put(171, (short) 15);
            itemDataTable.put(175, (short) 5);
            itemDataTable.put(188, (short) 0);
            itemDataTable.put(189, (short) 0);
            itemDataTable.put(190, (short) 0);
            itemDataTable.put(191, (short) 0);
            itemDataTable.put(192, (short) 0);
            itemDataTable.put(198, (short) 15);
            itemDataTable.put(199, (short) 0);
            itemDataTable.put(200, (short) 0);
            itemDataTable.put(321, (short) 0);
            itemDataTable.put(323, (short) 0);
            itemDataTable.put(355, (short) 0);
            itemDataTable.put(389, (short) 0);
            itemDataTable.put(390, (short) 0);
            itemDataTable.put(397, (short) 15);
            itemDataTable.put(416, (short) 0);
            itemDataTable.put(425, (short) 15);
            itemDataTable.put(426, (short) 0);
            itemDataTable.put(176, (short) 15);
            itemDataTable.put(177, (short) 15);

            // Redstone
            itemDataTable.put(23, (short) 9);
            itemDataTable.put(25, (short) 0);
            itemDataTable.put(29, (short) 15);
            itemDataTable.put(33, (short) 15);
            itemDataTable.put(46, (short) 0);
            itemDataTable.put(55, (short) 15);
            itemDataTable.put(64, (short) 15);
            itemDataTable.put(69, (short) 15);
            itemDataTable.put(70, (short) 15);
            itemDataTable.put(71, (short) 15);
            itemDataTable.put(72, (short) 15);
            itemDataTable.put(76, (short) 15);
            itemDataTable.put(77, (short) 15);
            itemDataTable.put(93, (short) 15);
            itemDataTable.put(94, (short) 15);
            itemDataTable.put(96, (short) 15);
            itemDataTable.put(107, (short) 15);
            itemDataTable.put(123, (short) 0);
            itemDataTable.put(131, (short) 15);
            itemDataTable.put(143, (short) 15);
            itemDataTable.put(146, (short) 15);
            itemDataTable.put(147, (short) 15);
            itemDataTable.put(148, (short) 15);
            itemDataTable.put(149, (short) 15);
            itemDataTable.put(150, (short) 15);
            itemDataTable.put(151, (short) 15);
            itemDataTable.put(152, (short) 0);
            itemDataTable.put(154, (short) 15);
            itemDataTable.put(158, (short) 15);
            itemDataTable.put(167, (short) 15);
            itemDataTable.put(178, (short) 15);
            itemDataTable.put(183, (short) 15);
            itemDataTable.put(184, (short) 15);
            itemDataTable.put(185, (short) 15);
            itemDataTable.put(186, (short) 15);
            itemDataTable.put(187, (short) 15);
            itemDataTable.put(193, (short) 15);
            itemDataTable.put(194, (short) 15);
            itemDataTable.put(195, (short) 15);
            itemDataTable.put(196, (short) 15);
            itemDataTable.put(197, (short) 15);
            itemDataTable.put(324, (short) 0);
            itemDataTable.put(330, (short) 0);
            itemDataTable.put(331, (short) 0);
            itemDataTable.put(356, (short) 0);
            itemDataTable.put(404, (short) 0);
            itemDataTable.put(427, (short) 0);
            itemDataTable.put(428, (short) 0);
            itemDataTable.put(429, (short) 0);
            itemDataTable.put(430, (short) 0);
            itemDataTable.put(431, (short) 0);

            // Transportation
            itemDataTable.put(27, (short) 15);
            itemDataTable.put(28, (short) 15);
            itemDataTable.put(66, (short) 15);
            itemDataTable.put(157, (short) 15);
            itemDataTable.put(328, (short) 0);
            itemDataTable.put(329, (short) 0);
            itemDataTable.put(333, (short) 0);
            itemDataTable.put(342, (short) 0);
            itemDataTable.put(343, (short) 0);
            itemDataTable.put(398, (short) 0);
            itemDataTable.put(407, (short) 0);
            itemDataTable.put(408, (short) 0);
            itemDataTable.put(443, (short) 0);
            itemDataTable.put(444, (short) 0);
            itemDataTable.put(445, (short) 0);
            itemDataTable.put(446, (short) 0);
            itemDataTable.put(447, (short) 0);
            itemDataTable.put(448, (short) 0);

            // Miscellaneous
            itemDataTable.put(138, (short) 0);
            itemDataTable.put(325, (short) 0);
            itemDataTable.put(326, (short) 0);
            itemDataTable.put(327, (short) 0);
            itemDataTable.put(332, (short) 0);
            itemDataTable.put(334, (short) 0);
            itemDataTable.put(339, (short) 0);
            itemDataTable.put(340, (short) 0);
            itemDataTable.put(341, (short) 0);
            itemDataTable.put(352, (short) 0);
            itemDataTable.put(358, (short) 1023);
            itemDataTable.put(368, (short) 0);
            itemDataTable.put(381, (short) 0);
            itemDataTable.put(383, (short) 0);
            itemDataTable.put(384, (short) 0);
            itemDataTable.put(385, (short) 0);
            itemDataTable.put(386, (short) 0);
            itemDataTable.put(395, (short) 0);
            itemDataTable.put(401, (short) 0);
            itemDataTable.put(402, (short) 0);
            itemDataTable.put(417, (short) 0);
            itemDataTable.put(418, (short) 0);
            itemDataTable.put(419, (short) 0);
            itemDataTable.put(2256, (short) 0);
            itemDataTable.put(2257, (short) 0);
            itemDataTable.put(2258, (short) 0);
            itemDataTable.put(2259, (short) 0);
            itemDataTable.put(2260, (short) 0);
            itemDataTable.put(2261, (short) 0);
            itemDataTable.put(2262, (short) 0);
            itemDataTable.put(2263, (short) 0);
            itemDataTable.put(2264, (short) 0);
            itemDataTable.put(2265, (short) 0);
            itemDataTable.put(2266, (short) 0);

            // Foodstuffs
            itemDataTable.put(260, (short) 0);
            itemDataTable.put(282, (short) 0);
            itemDataTable.put(297, (short) 0);
            itemDataTable.put(319, (short) 0);
            itemDataTable.put(320, (short) 0);
            itemDataTable.put(322, (short) 1);
            itemDataTable.put(349, (short) 3);
            itemDataTable.put(350, (short) 1);
            itemDataTable.put(354, (short) 0);
            itemDataTable.put(357, (short) 0);
            itemDataTable.put(360, (short) 0);
            itemDataTable.put(363, (short) 0);
            itemDataTable.put(364, (short) 0);
            itemDataTable.put(365, (short) 0);
            itemDataTable.put(366, (short) 0);
            itemDataTable.put(367, (short) 0);
            itemDataTable.put(375, (short) 0);
            itemDataTable.put(391, (short) 0);
            itemDataTable.put(392, (short) 0);
            itemDataTable.put(393, (short) 0);
            itemDataTable.put(394, (short) 0);
            itemDataTable.put(400, (short) 0);
            itemDataTable.put(411, (short) 0);
            itemDataTable.put(412, (short) 0);
            itemDataTable.put(413, (short) 0);
            itemDataTable.put(423, (short) 0);
            itemDataTable.put(424, (short) 0);
            itemDataTable.put(434, (short) 0);
            itemDataTable.put(436, (short) 0);

            // Tools
            itemDataTable.put(256, (short) 250);
            itemDataTable.put(257, (short) 250);
            itemDataTable.put(258, (short) 250);
            itemDataTable.put(259, (short) 64);
            itemDataTable.put(269, (short) 59);
            itemDataTable.put(270, (short) 59);
            itemDataTable.put(271, (short) 59);
            itemDataTable.put(273, (short) 131);
            itemDataTable.put(274, (short) 131);
            itemDataTable.put(275, (short) 131);
            itemDataTable.put(277, (short) 1561);
            itemDataTable.put(278, (short) 1561);
            itemDataTable.put(279, (short) 1561);
            itemDataTable.put(284, (short) 32);
            itemDataTable.put(285, (short) 32);
            itemDataTable.put(286, (short) 32);
            itemDataTable.put(290, (short) 59);
            itemDataTable.put(291, (short) 131);
            itemDataTable.put(292, (short) 250);
            itemDataTable.put(293, (short) 1561);
            itemDataTable.put(294, (short) 32);
            itemDataTable.put(345, (short) 0);
            itemDataTable.put(346, (short) 64);
            itemDataTable.put(347, (short) 0);
            itemDataTable.put(359, (short) 238);
            itemDataTable.put(420, (short) 0);
            itemDataTable.put(421, (short) 0);

            // Weapons & Armor
            itemDataTable.put(261, (short) 384);
            itemDataTable.put(262, (short) 0);
            itemDataTable.put(263, (short) 250);
            itemDataTable.put(268, (short) 59);
            itemDataTable.put(272, (short) 131);
            itemDataTable.put(276, (short) 1561);
            itemDataTable.put(283, (short) 32);
            itemDataTable.put(298, (short) 55);
            itemDataTable.put(299, (short) 80);
            itemDataTable.put(300, (short) 75);
            itemDataTable.put(301, (short) 65);
            itemDataTable.put(302, (short) 165);
            itemDataTable.put(303, (short) 240);
            itemDataTable.put(304, (short) 225);
            itemDataTable.put(305, (short) 195);
            itemDataTable.put(306, (short) 165);
            itemDataTable.put(307, (short) 240);
            itemDataTable.put(308, (short) 224);
            itemDataTable.put(309, (short) 195);
            itemDataTable.put(310, (short) 363);
            itemDataTable.put(311, (short) 528);
            itemDataTable.put(312, (short) 495);
            itemDataTable.put(313, (short) 429);
            itemDataTable.put(314, (short) 77);
            itemDataTable.put(315, (short) 111);
            itemDataTable.put(316, (short) 105);
            itemDataTable.put(317, (short) 91);
            itemDataTable.put(403, (short) 0);
            itemDataTable.put(439, (short) 0);
            itemDataTable.put(440, (short) 0);
            itemDataTable.put(442, (short) 0);

            // Brewing
            itemDataTable.put(370, (short) 0);
            itemDataTable.put(373, (short) 0);
            itemDataTable.put(374, (short) 0);
            itemDataTable.put(376, (short) 0);
            itemDataTable.put(377, (short) 0);
            itemDataTable.put(378, (short) 0);
            itemDataTable.put(379, (short) 0);
            itemDataTable.put(380, (short) 0);
            itemDataTable.put(382, (short) 0);

            // Materials
            itemDataTable.put(263, (short) 1);
            itemDataTable.put(264, (short) 0);
            itemDataTable.put(265, (short) 0);
            itemDataTable.put(266, (short) 0);
            itemDataTable.put(280, (short) 0);
            itemDataTable.put(281, (short) 0);
            itemDataTable.put(287, (short) 0);
            itemDataTable.put(288, (short) 0);
            itemDataTable.put(289, (short) 0);
            itemDataTable.put(295, (short) 0);
            itemDataTable.put(296, (short) 0);
            itemDataTable.put(318, (short) 0);
            itemDataTable.put(334, (short) 0);
            itemDataTable.put(336, (short) 0);
            itemDataTable.put(337, (short) 0);
            itemDataTable.put(338, (short) 0);
            itemDataTable.put(344, (short) 0);
            itemDataTable.put(348, (short) 0);
            itemDataTable.put(351, (short) 15);
            itemDataTable.put(353, (short) 0);
            itemDataTable.put(361, (short) 0);
            itemDataTable.put(362, (short) 0);
            itemDataTable.put(369, (short) 0);
            itemDataTable.put(371, (short) 0);
            itemDataTable.put(372, (short) 0);
            itemDataTable.put(388, (short) 0);
            itemDataTable.put(399, (short) 0);
            itemDataTable.put(405, (short) 0);
            itemDataTable.put(406, (short) 0);
            itemDataTable.put(409, (short) 0);
            itemDataTable.put(410, (short) 0);
            itemDataTable.put(415, (short) 0);
            itemDataTable.put(432, (short) 0);
            itemDataTable.put(433, (short) 0);
            itemDataTable.put(435, (short) 0);
        }
    }
}
