# Introduction #

The following formats can be used to create files for loading new restaurants into the database.


# Details #
## Restaurant ##
For now our restaurant table only contains a name and a list of cuisines
The file should contain the restaurant name and then a list of cuisines.  These should be comma separated.

The list of cuisines should also be comma separated but should be enclosed in "

&lt;list&gt;

" so that it is treated as a string.

Create a table of Restaurant attributes you would like to see here if you feel we need more attributes.

## Menus ##
The following comma separated list of attributes should be created for each menu item.  Strings should be enclosed in ""

File called **menu.csv**

  * Category (see the text list below for the acceptable category options)
  * Name of the Dish (as appears on the menu)
  * Description of the dish (if available from the menu)
  * Additional info (regarding additional or promotional options)
  * Cost
  * Vegetarion (indication true/false if the dish is vegetarian)
  * Healthy (indication true/false if the dish is a healthy option)
  * Special (indication true/false if the dish is a special offer)

### Categories ###
| **Category** | **Description** |
|:-------------|:----------------|
| Light Meals  | Starters and Entres |
| Regular Meals | Meals for 1     |
| Combo Meals  | _self explanatory_ |
| Speciality Meals | restaurant specials |
| Family Meals | Meals for groups |
| Kids Meals   | Kiddies portions |
| Salads       | _green stuff that your food eats_ |
| Extras       | does not fit in the other categories |
| Sides        | Chips, Condiments |
| Drinks       | Cool drinks, Milkshakes etc |
| Desserts     | Ice-creams, Cakes |

We might need to add more categories if we find menus that have more categories than this.  Try to link categories that appear on the menus with what we have here so that we can have a consistent feel across all menus.

`*` _If you feel that we should rather go for a look and feel that represents the actual menu then we can do away with the categories listed here and use the categories present on each menu._

## Branches ##
The following comma separated list of attributes should be created for each branch.  Strings should be enclosed in ""

File called **branch.csv**

  * Name (if not present add as suburb, if multiple within suburb use address or add some sort of identifier)
  * Province
  * Suburb
  * Address
  * Telephone No
  * Location Latitude ( format decimal degrees (+/-) 26.45E)
  * Location Longitude ( format decimal degrees (+/-) 26.45S)
  * Halaal (indication true/false if the branch is certified Halaal)
  * Kosher (indication true/false if the branch is a certified Kosher)

`*`_add your recommendations for new attributes in tables under each heading.  We can discuss and add what we feel is required_