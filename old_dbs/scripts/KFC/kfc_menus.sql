--ALTER TABLE restaurant ADD COLUMN slogan TEXT;

--INSERT INTO restaurant (name, cuisines, logo, slogan)
--VALUES ('KFC', 'Fried Chicken Fast Food Burgers', 'ic_rest_kfc.png', 'So Good');

--INSERT INTO menu_category (master_category, category) 
--VALUES (6,'On Promotion');
--51

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,51,'Famous Bowl','A delicious mix of mash, gravy, corn, cheese and chicken strips','34.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,51,'Big 7 Meal','7 Pieces of original recipe chicken cooked to golden perfection and 2 large chips','92.90');

--INSERT INTO menu_category (master_category, category, description) 
--VALUES (2,'Breakfast', 'Only available at selected KFC Breakfast stores from 6am - 10.30am. *Accessories excluded.');
--52

INSERT INTO menu (rest_id, category, dish, description,cost, additional) 
VALUES (6,52,'a.m. Riser Deal','a.m Riser / Muffin (breakfast or Chocolate) / LiquFruit (or cappuccino)','39.90', 'A convenient, easy choice with all your favorite breakfast products bundled together. *Glassware excluded.');

INSERT INTO menu (rest_id, category, dish, description,cost, additional) 
VALUES (6,52,'a.m. Toasted Deal','Toasted Sandwich (Any) / Muffin (Chocolate or Breakfast) / Cappuccino (or LiquiFruit)','38.90', 'A convenient, easy choice with all your favorite breakfast products bundled together. *Glassware excluded.');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m Krusher','An icy, sippable treat full of real berry fruit bitz, fresh yoghurt, soft-serve and crushed ice. ','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'Chocolate Muffin','Chocolate Muffin filled with Chocolate bits and topped with chocolate chips.','14.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'Breakfast Muffin','A healthier muffin with bran and cranberries topped with oats and linseed.','14.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Oats','Freshly cooked oats, served warm, with a sweet apple topping.','11.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Muesli cup','Crunchy muesli, smooth vanilla yoghurt and fresh seasonal fruit, layered in a cup.','19.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Fruit Cup','Fresh seasonal fruit, layered in a cup.','24.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Snacker ','An egg, slice of cheese and Colonel Dressing on a small burger bun.','9.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Snacker with Snack Patty ','An egg, slice of cheese, chicken patty and Colonel Dressing on a small burger bun.','14.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Toasted Sandwich - Cheese & Tomato','Toasted Cheese and Tomato on either White or Brown bread.','16.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Toasted Sandwich - Chicken Mayonnaise','Toasted Chicken Mayonaise on either White or Brown bread.','16.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Toasted Sandwich - Egg, Cheese & Tomato','Toasted Egg, Cheese and Tomato on either White or Brown bread.','16.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Mini Twister with Hashbrown','Crispy hashbrown, cheese, egg, diced tomato, tomato relish and Colonel dressing wrapped in a warm toasted tortilla.','16.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Riser','A freshly made egg with a slice of tomato, tomato relish  a choice of either a hashbrown or a Streetwise patty, and cheese slice in a delicious soft English muffin.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Deluxe Burger without an Egg','Succulent Original Recipe chicken fillet, topped with a hashbrown, cheese slice, tomato relish and creamy dressing in a soft bun.','29.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Deluxe Burger with an Egg','Succulent Original Recipe chicken fillet, topped with a hashbrown, cheese slice, tomato relish, creamy dressing and an egg in a soft bun.','33.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'a.m. Streetwise 2 with Small Chips','Two pieces of Original Recipe chicken with small chips.','25.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'Cappuccino','This cappuccino is made from 100% Arabica premium roast coffee beans, freshly ground for every cup.','14.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'KFC Coffee','KFC coffee is made from 100% Arabica premium roast coffee beans, freshly ground for every cup.','9.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'Espresso','This espresso is made from 100% Arabica premium roast coffee beans, freshly ground for every cup.','9.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,52,'Hot Chocolate ','Our delicious & frothy Hot Chocolate.','14.90');

--INSERT INTO menu_category (master_category, category) 
--VALUES (2,'Ka-Ching Deals');
--53

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,53,'Chips','A small portion of chips, crispy on the outside, soft and fluffy on the inside.','5.00', 'Small');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,53,'Snack Burger','A delicious chicken patty, served with shredded lettuce and Colonel dressing on a warmed bun.','10.00');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,53,'Milkshake','A delectably delicious creamy milkshake. Available in strawberry and chocolate flavours.','10.00', 'Small');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,53,'Sundae','Creamy soft-serve ice-cream, topped with either chocolate or mixed berry sauce.','11.00', 'Small');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,53,'Pops','Bite-sized pieces of chicken, tender on the inside and crunchy on the outside, all flavoured with our world-renowned seasoning.','12.00', 'Small');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,53,'4 Hot Zinger Wings','4 Succulent winglets breaded in our hot Zinger breading to give you the delicious hot flavour you love, cooked to crispy-brown perfection.','15.00');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,53,'Mini Twister','A warm tortilla wrapped around a deliciously crispy chicken strip, shredded crisp lettuce, diced tomato and a dash of Colonel dressing.','17.00');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,53,'Mini-Bowl','A delicious mix of mash, gravy, corn, cheese and KFC pops.','18.00');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,53,'Double Take','Two KFC snack burgers. Each with a delicious chicken patty, served with shredded lettuce and Colonel dressing on a warmed bun.','19.00');

--INSERT INTO menu_category (master_category, category) 
--VALUES (2,'Streetwise Meals');
--54

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise Zinger 4 - with pap and gravy','4 succulent wings breaded in our hot Zinger breading to give you the delicious hot flavour you love, cooked to crispy-brown perfection. Served with a regular portion of pap & small gravy.','19.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise Zinger 4 - with mash and gravy','4 succulent wings breaded in our hot Zinger breading to give you the delicious hot flavour you love, cooked to crispy-brown perfection. Served with a regular portion of mash  and gravy.','19.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise 2 - With Small Chips','2 Pieces of chicken cooked to golden perfection and a small portion of chips.','25.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise 3','3 Pieces of chicken cooked to golden perfection and a small portion of chips.','35.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise 2 - With Pap','2 Pieces of chicken cooked to golden perfection and a regular portion of pap & small gravy.','23.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise 1 - With Pap','1 Piece of chicken cooked to golden perfection with a regular portion of pap and small gravy.','15.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise 1 - With Mash','1 Piece of chicken cooked to golden perfection, a warm mini loaf and large mash & delicious gravy.','19.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise 5','5 Pieces of Original Recipe chicken cooked to golden perfection and a large portion of chips.','61.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise Feast (8-piece)','8 Pieces of chicken cooked to golden perfection, 3 warm mini loaves and 2 regular portions of delicious gravy.','96.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,54,'Streetwise Feast (6-piece)','6 Pieces of chicken cooked to golden perfection, 3 warm mini loaves and 2 regular portions of delicious gravy.','76.90');

--INSERT INTO menu_category (master_category, category) 
--VALUES (6,'Twisters');
--55

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,55,'Classic Twister Meal','A warm tortilla wrapped around 2 deliciously crispy chicken strips , shredded crisp lettuce, diced tomato and a dash of Colonel dressing. Served with a regular ice-cold drink and regular chips.  ','43.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,55,'Classic Twister','A warm tortilla wrapped around 2 deliciously crispy chicken strips, shredded crisp lettuce, diced tomato and a dash of Colonel dressing.','26.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,55,'Sweet Chilli Twister Meal','A warm tortilla wrapped around 2 deliciously crispy chicken strips, shredded crisp lettuce, diced tomato, topped with colonel dressing and sweet chilli sauce. Served with a regular ice-cold drink and regular chips. ','43.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,55,'Sweet Chilli Twister','A warm tortilla wrapped around 2 deliciously crispy chicken strips, shredded crisp lettuce, diced tomato, topped with colonel dressing and sweet chilli sauce. ','26.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,55,'Grilled Twister Meal','A warm tortilla wrapped around a grilled fillet strip, shredded crisp lettuce, diced tomato, and a dash of Colonel dressing. Served with a regular ice-cold drink and regular chips.  ','43.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,55,'Grilled Twister','A warm tortilla wrapped around a grilled fillet strip, shredded crisp lettuce, diced tomato, and a dash of Colonel dressing.','26.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,55,'Hawaiian Twister Meal','A warm tortilla wrapped around 2 deliciously crispy chicken strips, pineapple pieces, cheese, shredded crisp lettuce, diced tomato and topped with original Colonel sauce. Served with a regular ice-cold drink and regular chips.  ','51.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,55,'Hawaiian Twister','A warm tortilla wrapped around 2 deliciously crispy chicken strips, pineapple pieces, cheese, shredded crisp lettuce, diced tomato and topped with original Colonel sauce.','34.90');

--INSERT INTO menu_category (master_category, category) 
--VALUES (3,'Burgers');
--56

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Colonel Burger Meal','A tender and tasty marinated fillet cooked golden-brown, topped with fresh lettuce, a slice of tomato and Colonel dressing on a burger bun. Served with a regular ice-cold drink and regular chips.  ','42.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Colonel Burger','A tender and tasty marinated fillet cooked golden-brown, topped with fresh lettuce, a slice of tomato and Colonel dressing on a burger bun.','25.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Zinger Burger Meal','A delicious chicken fillet covered in a hot and spicy Zinger breading, topped with fresh lettuce, a slice of tomato and Colonel dressing or spice it up with hot Zinger Dressing. Served with a regular ice-cold drink and regular chips.  ','42.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Zinger Burger','A delicious chicken fillet covered in a hot and spicy Zinger breading, topped with fresh lettuce, a slice of tomato and Colonel dressing or spice it up with hot Zinger Dressing. Warning: It’s hot!','25.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Grilled Burger Meal','A grilled fillet served on a burger bun with shredded lettuce, a slice of tomato and Colonel dressing. Served with a regular ice-cold drink and regular chips.','42.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Grilled Burger','A grilled fillet served on a burger bun with shredded lettuce, a slice of tomato and Colonel dressing.','25.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Tower Burger Meal','A tender and tasty breast fillet cooked golden-brown, served with a crispy crumbed-coated hash brown, a cheese slice, shredded lettuce, a slice of tomato, and Colonel dressing on a burger bun. Served with a regular ice-cold drink and regular chips.  ','51.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Tower Burger','A tender and tasty breast fillet cooked golden-brown, served with a crispy crumbed-coated hash brown, a cheese slice, shredded lettuce, a slice of tomato, and Colonel dressing on a burger bun.','34.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Hawaiian Burger Meal','A tasty breast fillet cooked golden-brown, served with shredded lettuce, a slice of pineapple and cheese. All with Colonel dressing on a burger bun. Served with a regular ice-cold drink and regular chips.  ','51.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,56,'Hawaiian Burger','A tasty breast fillet cooked golden-brown, served with shredded lettuce, a slice of pineapple and cheese. All with Colonel dressing on a burger bun.','34.90');

--INSERT INTO menu_category (master_category, category) 
--VALUES (4,'Big Eats');
--57

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,57,'Colonel Meal 2 Piece','2 Pieces of original recipe chicken, 3 Hot Zinger wings, regular chips and a 330ml can','49.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,57,'Colonel Meal 3 Piece','3 Pieces of original recipe chicken, 3 Hot Zinger wings, regular chips and a 330ml can','59.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,57,'Fully Loaded Box Meal','Colonel Burger, 1 piece of chicken cooked to golden perfection, regular mash and gravy, regular chips & an ice-cold drink. ','61.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,57,'Wicked Zinger Box Meal','Hot Zinger Burger, 4 Hot Zinger Wings, regular mash and our delicious gravy, regular chips & an ice-cold drink. ','64.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,57,'Boxmaster Meal','A warm tortilla wrapped around a delicious fillet - your choice of hot Zinger, original or grilled - a slice of cheese, hash brown, crisp lettuce, fresh tomato and a dash of Colonel dressing. Served with a regular ice-cold drink and regular chips.  ','53.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,57,'Boxmaster','A warm tortilla wrapped around a delicious fillet - your choice of hot Zinger, original or grilled - a slice of cheese, hash brown, crisp lettuce, fresh tomato and a dash of Colonel dressing.','36.90');

--INSERT INTO menu_category (master_category, category) 
--VALUES (5,'Family Meals');
--58

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Big 7 Meal','7 pieces of original recipe chicken and 2 large chips','92.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Streetwise 5','5 Pieces of Original Recipe chicken cooked to golden perfection and a large portion of chips.','61.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Streetwise Feast (8-piece)','8 Pieces of chicken cooked to golden perfection, 3 warm mini loaves and 2 delicious regular gravies.','96.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Streetwise Feast (6-piece)','6 Pieces of chicken cooked to golden perfection, 3 warm mini loaves and 2 delicious regular gravies.','76.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Family Treat (10-piece, with any 3 sides)','10 pieces of chicken served with 2 large portions of chips and your selection of any 3 sides: 2 Sparkling Krushers or 2 small Milkshakes (drink formats vary from store to store), 1 Mini Bowl, 2 regular Mash & Gravies, 2 regular Coleslaws, 2 Mini Loaves, 4 Hot Zinger Wings, 2 Snack Burgers or 2 small Pops. ','159.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Family Treat (8-piece, with any 3 sides)','8 pieces of chicken served with 2 large portions of chips and your selection of any 3 sides: 2 Sparkling Krushers or 2 small Milkshakes (drink formats vary from store to store), 1 Mini Bowl, 2 regular Mash & Gravies, 2 regular Coleslaws, 2 Mini Loaves, 4 Hot Zinger Wings, 2 Snack Burgers or 2 small Pops. ','139.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Family Treat (6-piece, with any 3 sides)','6 pieces of chicken served with 2 large portions of chips and your selection of any 3 sides: 2 Sparkling Krushers or 2 small Milkshakes (drink formats vary from store to store), 1 Mini Bowl, 2 regular Mash & Gravies, 2 regular Coleslaws, 2 Mini Loaves, 4 Hot Zinger Wings, 2 Snack Burgers or 2 small Pops. ','119.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Family Box Meal','With 2 Colonel Burgers, 2 Snack Burgers, 2 Small Pops, 2 Regular chips, 2 Small Chips and a 1 litre Coke','139.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'21 Piece Bucket','21 Pieces of juicy chicken seasoned with the 11 secret herbs and spices of Original Recipe® chicken, marinated and cooked to perfection.','199.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'15 Piece','15  Pieces of juicy chicken seasoned with the 11 secret herbs and spices of Original Recipe® chicken, marinated and cooked to perfection.','159.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'9 Piece','9 Pieces of juicy chicken seasoned with the 11 secret herbs and spices of Original Recipe® chicken, marinated and cooked to perfection.','104.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Variety Bucket With Chips','6 Pieces of juicy chicken, 15 delicious hot Zinger Wings, 1 large Pops and 2 large chips.','169.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,58,'Variety Bucket','6 Pieces of juicy chicken, 15 delicious hot Zinger Wings and 1 large Pops.','149.90');

--INSERT INTO menu_category (master_category, category, additional) 
--VALUES (12,'Treats', '*Accessories excluded');
--59

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Mixed Berry Kream Ball ','Delicious layers of shortbread biscuit crumble, creamy soft-serve ice-cream and indulgent mixed berry sauce.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Choc Crumble Kream Ball ','Delicious layers of Oreo biscuit crumble, creamy soft-serve ice-cream and indulgent chocolate sauce.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Milkybar Kream Ball ','Delicious layers of shortbread biscuit crumble, creamy soft-serve ice-cream and indulgent Milkybar sauce.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Chocolate Sundae','Soft-serve ice-cream, topped with an indulgent chocolate sauce.','11.00');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Mixed Berry Sundae','Soft-serve ice-cream, topped with a delicious mixed berry sauce.','11.00');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Soft Twirl','Mouth watering soft-serve ice-cream in a crispy cone.','3.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Soft Twirl with Flake','Mouth watering soft-serve ice-cream in a crispy cone with a flake.','6.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Chocolate Milkshake - Regular','A delectably delicious creamy chocolate milkshake.','12.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Chocolate Milkshake - Small','A delectably delicious creamy chocolate milkshake.','10.00');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Strawberry Milkshake - Regular','A delectably delicious creamy strawberry milkshake.','12.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Strawberry Milkshake - Small','A delectably delicious creamy strawberry milkshake.','10.00');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Oreo Krusher','An icy, sippable treat full of real Oreo bitz, soft-serve and crushed ice.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Verry Berry Krusher','An icy, sippable treat full of real berry fruit bitz, fresh yoghurt, soft-serve and crushed ice.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Blueberry Shortcake Krusher','An icy, sippable treat made with delicious creamy soft-serve, crushed ice and topped off with the taste of delicious, creamy Blueberry Shortcake.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Strawberry Sunrise Sparkling Krusher','An icy, carbonated beverage with a Sprite base, mixed with pink strawberry flavoured syrup and garnished with lemon wedges.','10.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,59,'Virgin Mojito Sparkling Krusher','An icy, carbonated beverage with a Sprite base, mixed with mint flavoured syrup, garnished with lemon wedges and mint leaves.','10.90');

--INSERT INTO menu_category (master_category, category, additional) 
--VALUES (7,'Kids', '*Sorry we no longer sell toys. We''d rather serve treats you can eat.');
--60

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,60,'1 Piece Meal','1 Piece of succulent chicken, 250ml LiquiFruit and small chips. ','24.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,60,'Mini Burger Meal','Snack burger, 250ml LiquiFruit and small chips.','24.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,60,'Pops Meal','Small portion of bite-sized, succulent Pops, small chips and an ice-cold drink.','24.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,60,'Mini Twister Meal','A delicious grilled mini Twister with cheese, fruit stick and a 350ml still water.','31.90');

--INSERT INTO menu_category (master_category, category) 
--VALUES (10,'Add On');
--61

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,61,'8 Hot Zinger Wings','8 Succulent winglets breaded in our hot Zinger breading to give you the delicious hot flavour you love, cooked to crispy-brown perfection. ','28.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,61,'Sprinkle Pops','Bite-sized pieces of chicken, tender on the inside and crunchy on the outside, flavoured with our world-renowned seasoning and served with an added sprinkle of fruit chutney.','26.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,61,'1 Piece','1 Piece of juicy chicken seasoned with the 11 secret herbs and spices of Original Recipe® chicken, marinated and cooked to perfection.','13.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,61,'Mini Loaf','Fresh Mini Loaf','4.40');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,61,'Chips','A large portion of chips, crispy on the outside, soft and fluffy on the inside.','15.90', 'Large');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,61,'Chips','A regular portion of chips, crispy on the outside, soft and fluffy on the inside.','11.90', 'Regular');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,61,'Mash & Gravy','A large portion of mash and delicious gravy.','9.90', 'Large');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,61,'Mash & Gravy','A regular portion of mash and delicious gravy.','7.90', 'Regular');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,61,'Gravy','Delicious tasting gravy.','6.90', 'Large');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,61,'Gravy','Delicious tasting gravy.','5.90', 'Regular');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,61,'Coleslaw','A large portion of freshly prepared cabbage, onions and carrots in a delicious dressing.','15.90', 'Large');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,61,'Coleslaw','A regular portion of freshly prepared cabbage, onions and carrots in a delicious dressing.','9.90', 'Regular');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,61,'KFC Salad','A crispy salad with fresh lettuce, tomato, corn and thinly sliced beetroot and carrots, topped with your choice of a Original Recipe, Zinger or Grilled fillet. Served with Ina Paarman’s delicious Honey & Mustard dressing. *Glassware exluded','38.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,61,'Green Salad','A crispy salad with fresh lettuce, tomato, corn and thinly sliced beetroot and carrots served with Ina Paarman’s delicious Honey & Mustard dressing. ','24.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,61,'Corn in a Tub - Large','A large portion of corn in a tub.','12.90');

--INSERT INTO menu_category (master_category, category) 
--VALUES (11,'Drinks');
--62

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Oreo Krusher','An icy, sippable treat full of real Oreo bitz, soft-serve and crushed ice. *Accessories excluded.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Verry Berry Krusher','An icy, sippable treat full of real berry fruit bitz, fresh yoghurt, soft-serve and crushed ice. *Accessories excluded.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Blueberry Shortcake Krusher','An icy, sippable treat made with delicious creamy soft-serve, crushed ice and topped off with the taste of delicious, creamy Blueberry Shortcake. *Accessories excluded.','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Strawberry Sunrise Sparkling Krusher ','An icy, carbonated beverage with a Sprite base, mixed with pink strawberry flavoured syrup and garnished with lemon wedges. *Accessories excluded.','10.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Virgin Mojito Sparkling Krusher ','An icy, carbonated beverage with a Sprite base, mixed with mint flavoured syrup, garnished with lemon wedges and mint leaves. *Accessories excluded.','10.90');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,62,'Strawberry Milkshake','A delectably delicious creamy strawberry milkshake.','12.90', 'Regular');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,62,'Strawberry Milkshake','A delectably delicious creamy strawberry milkshake.','10.00', 'Small');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,62,'Chocolate Milkshake','A delectably delicious creamy chocolate milkshake.','12.90', 'Regular');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,62,'Chocolate Milkshake','A delectably delicious creamy chocolate milkshake.','10.00', 'Small');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Can 330ml ','Available in Coke, Coke Zero, Fanta, Sprite and Cream Soda flavours. ','11.90');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,62,'Large Drink','Available in Coke, Coke Zero, Fanta, Sprite and Cream Soda flavours','12.90', 'Large');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,62,'Regular Drink','Available in Coke, Coke Zero, Fanta, Sprite and Cream Soda flavours','9.90', 'Regular');

INSERT INTO menu (rest_id, category, dish, description,cost, portion_size) 
VALUES (6,62,'Small Drink','Available in Coke, Coke Zero, Fanta, Sprite and Cream Soda flavours','7.90', 'Small');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Buddy Bottle 500ml','Available in a range of flavours.','13.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'1L Coke','','17.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'2L Coke ','','24.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Grapetiser 350ml','','14.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Appletiser 350ml','','14.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Powerade 500ml','Available in a range of flavours.','14.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Valpre Still Spring Water','','10.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'Bonaqua 500ml Still','','9.90');

INSERT INTO menu (rest_id, category, dish, description,cost) 
VALUES (6,62,'LiquiFruit 250ml','','9.90');

