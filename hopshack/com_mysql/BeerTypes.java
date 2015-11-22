package hopshack.com_mysql;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class BeerTypes {
	//Array of style strings!
	   public static String[] types =
	   {"Altbier",
	   "Amber Ale",
	   "American Lager",
	   "American Wheat Ales",
	   "American-Style Brett Ale",
	   "American-Style Sour Ale",
	   "Barley Wine: American-Stlye",
	   "Barley Wine: English-Style",
	   "Belgian Golden Strong Ale",
	   "Berliner Weisse",
	   "Biere de Champagne",
	   "Biere de Garde",
	   "Bock",
	   "Brown Ale",
	   "California Common",
	   "Cream Ale",
	   "Doppelbock",
	   "Dortmunder Export",
	   "Dunkelweizen",
	   "Eisbock",
	   "Extra Special Bitter",
	   "Flanders Oud Bruin",
	   "Flanders Red Ale",
	   "Fresh Hop Ale",
	   "Fruit Lambic",
	   "Gose",
	   "Gueuze",
	   "Hefeweizen",
	   "IPA: Belgian",
	   "IPA: Black",
	   "IPA: Double",
	   "IPA: East Coast",
	   "IPA: English",
	   "IPA: Pacific Northwest",
	   "IPA: Red",
	   "IPA: Rye",
	   "IPA: Triple",
	   "IPA: West Coast",
	   "IPA: White",
	   "Kolsch",
	   "Maibock",
	   "Marzen",
	   "Munich Dunkel",
	   "Munich Helles",
	   "Pale Ale: American",
	   "Pale Ale: Belgian",
	   "Pale Ale: English",
	   "Pilsner: Czech",
	   "Pilsner: German",
	   "Porter: American",
	   "Porter: Baltic",
	   "Porter: British",
	   "Pumpkin Beer",
	   "Rauchbier",
	   "Saison",
	   "Schwarzbier",
	   "Scottish Ale/Wee Heavy",
	   "Standard and Best Bitter",
	   "Stout: American",
	   "Stout: Dry Irish",
	   "Stout: Foreign Extra",
	   "Stout: Imperial",
	   "Stout: Milk",
	   "Stout: Oatmeal",
	   "Trappist: Dubbel",
	   "Trappist: Quadrupel / Belgian Dark",
	   "Trappist: Tripel",
	   "Vienna Lager",
	   "Weizenbock",
	   "Wheat Wine",
	   "Witbier",
	   "Fruit/Vegetable Beer",
	   "Strong Ale",
	   "Smoked Beer/Gratzer",
	   "Ancient Herbed Ale/Gruit",
	   "Spiced/Herbed Ale",
	   "Rye/Roggenbier",
	   "Kellerbier/Zwickelbier"};
	   
	//type id to STRING of STYLE
	//Using switch statements for readability. break stops the thing early
		public String idToType(int id){
			String typeString = "Unknown";
			switch (id){
			case 0: typeString = "Altbier";
			break;
			case 1: typeString = "Amber Ale";
			break;
			case 2: typeString = "American Lager";
			break;
			case 3: typeString = "American Wheat Ales";
			break;
			case 4: typeString = "American-Style Brett Ale";
			break;
			case 5: typeString = "American-Style Sour Ale";
			break;
			case 6: typeString = "Barley Wine: American-Stlye";
			break;
			case 7: typeString = "Barley Wine: English-Style";
			break;
			case 8: typeString = "Belgian Golden Strong Ale";
			break;
			case 9: typeString = "Berliner Weisse";
			break;
			case 10: typeString = "Biere de Champagne";
			break;
			case 11: typeString = "Biere de Garde";
			break;
			case 12: typeString = "Bock";
			break;
			case 13: typeString = "Brown Ale";
			break;
			case 14: typeString = "California Common";
			break;
			case 15: typeString = "Cream Ale";
			break;
			case 16: typeString = "Doppelbock";
			break;
			case 17: typeString = "Dortmunder Export";
			break;
			case 18: typeString = "Dunkelweizen";
			break;
			case 19: typeString = "Eisbock";
			break;
			case 20: typeString = "Extra Special Bitter";
			break;
			case 21: typeString = "Flanders Oud Bruin";
			break;
			case 22: typeString = "Flanders Red Ale";
			break;
			case 23: typeString = "Fresh Hop Ale";
			break;
			case 24: typeString = "Fruit Lambic";
			break;
			case 25: typeString = "Gose";
			break;
			case 26: typeString = "Gueuze";
			break;
			case 27: typeString = "Hefeweizen";
			break;
			case 28: typeString = "IPA: Belgian";
			break;
			case 29: typeString = "IPA: Black";
			break;
			case 30: typeString = "IPA: Double";
			break;
			case 31: typeString = "IPA: East Coast";
			break;
			case 32: typeString = "IPA: English";
			break;
			case 33: typeString = "IPA: Pacific Northwest";
			break;
			case 34: typeString = "IPA: Red";
			break;
			case 35: typeString = "IPA: Rye";
			break;
			case 36: typeString = "IPA: Triple";
			break;
			case 37: typeString = "IPA: West Coast";
			break;
			case 38: typeString = "IPA: White";
			break;
			case 39: typeString = "Kolsch";
			break;
			case 40: typeString = "Maibock";
			break;
			case 41: typeString = "Marzen";
			break;
			case 42: typeString = "Munich Dunkel";
			break;
			case 43: typeString = "Munich Helles";
			break;
			case 44: typeString = "Pale Ale: American";
			break;
			case 45: typeString = "Pale Ale: Belgian";
			break;
			case 46: typeString = "Pale Ale: English";
			break;
			case 47: typeString = "Pilsner: Czech";
			break;
			case 48: typeString = "Pilsner: German";
			break;
			case 49: typeString = "Porter: American";
			break;
			case 50: typeString = "Porter: Baltic";
			break;
			case 51: typeString = "Porter: British";
			break;
			case 52: typeString = "Pumpkin Beer";
			break;
			case 53: typeString = "Rauchbier";
			break;
			case 54: typeString = "Saison";
			break;
			case 55: typeString = "Schwarzbier";
			break;
			case 56: typeString = "Scottish Ale/Wee Heavy";
			break;
			case 57: typeString = "Standard and Best Bitter";
			break;
			case 58: typeString = "Stout: American";
			break;
			case 59: typeString = "Stout: Dry Irish";
			break;
			case 60: typeString = "Stout: Foreign Extra";
			break;
			case 61: typeString = "Stout: Imperial";
			break;
			case 62: typeString = "Stout: Milk";
			break;
			case 63: typeString = "Stout: Oatmeal";
			break;
			case 64: typeString = "Trappist: Dubbel";
			break;
			case 65: typeString = "Trappist: Quadrupel / Belgian Dark";
			break;
			case 66: typeString = "Trappist: Tripel";
			break;
			case 67: typeString = "Vienna Lager";
			break;
			case 68: typeString = "Weizenbock";
			break;
			case 69: typeString = "Wheat Wine";
			break;
			case 70: typeString = "Witbier";
			break;
			case 71: typeString = "Fruit/Vegetable Beer";
			break;
			case 72: typeString = "Strong Ale";
			break;
			case 73: typeString = "Smoked Beer/Gratzer";
			break;
			case 74: typeString = "Ancient Herbed Ale/Gruit";
			break;
			case 75: typeString = "Spiced/Herbed Ale";
			break;
			case 76: typeString = "Rye/Roggenbier";
			break;
			case 77: typeString = "Kellerbier/Zwickelbier";
			break;
			}
			return typeString;

		}
		
		//type id to IMAGE of STYLE
		//Using switch statements for readability. break stops the thing early
			public int idToImage(int id){
				Integer imageId = R.drawable.brown;
				switch (id){
				case 0: imageId = R.drawable.altbier;
				break;
				case 1: imageId = R.drawable.amber;
				break;
				case 2: imageId = R.drawable.californiacommon;
				break;
				case 3: imageId = R.drawable.fruit;
				break;
				case 4: imageId = R.drawable.sour;
				break;
				case 5: imageId = R.drawable.sour;
				break;
				case 6: imageId = R.drawable.barleywine;
				break;
				case 7: imageId = R.drawable.barleywine;
				break;
				case 8: imageId = R.drawable.belgiangolden;
				break;
				case 9: imageId = R.drawable.gose;
				break;
				case 10: imageId = R.drawable.lambic;
				break;
				case 11: imageId = R.drawable.bieredegarde;
				break;
				case 12: imageId = R.drawable.bock;
				break;
				case 13: imageId = R.drawable.brown;
				break;
				case 14: imageId = R.drawable.californiacommon;
				break;
				case 15: imageId = R.drawable.paleale;
				break;
				case 16: imageId = R.drawable.bock;
				break;
				case 17: imageId = R.drawable.blond;
				break;
				case 18: imageId = R.drawable.weizenbock;
				break;
				case 19: imageId = R.drawable.eisbock;
				break;
				case 20: imageId = R.drawable.esb;
				break;
				case 21: imageId = R.drawable.sour;
				break;
				case 22: imageId = R.drawable.sour;
				break;
				case 23: imageId = R.drawable.pacificnorthwest;
				break;
				case 24: imageId = R.drawable.lambic;
				break;
				case 25: imageId = R.drawable.gose;
				break;
				case 26: imageId = R.drawable.amber;
				break;
				case 27: imageId = R.drawable.weizenbock;
				break;
				case 28: imageId = R.drawable.westcoastipa;
				break;
				case 29: imageId = R.drawable.black;
				break;
				case 30: imageId = R.drawable.doubleipa;
				break;
				case 31: imageId = R.drawable.pacificnorthwest;
				break;
				case 32: imageId = R.drawable.westcoastipa;
				break;
				case 33: imageId = R.drawable.pacificnorthwest;
				break;
				case 34: imageId = R.drawable.redipa;
				break;
				case 35: imageId = R.drawable.redipa;
				break;
				case 36: imageId = R.drawable.tripleipa;
				break;
				case 37: imageId = R.drawable.westcoastipa;
				break;
				case 38: imageId = R.drawable.witbier;
				break;
				case 39: imageId = R.drawable.blond;
				break;
				case 40: imageId = R.drawable.marzen;
				break;
				case 41: imageId = R.drawable.marzen;
				break;
				case 42: imageId = R.drawable.amber;
				break;
				case 43: imageId = R.drawable.blond;
				break;
				case 44: imageId = R.drawable.paleale;
				break;
				case 45: imageId = R.drawable.bieredegarde;
				break;
				case 46: imageId = R.drawable.englishpaleale;
				break;
				case 47: imageId = R.drawable.pils;
				break;
				case 48: imageId = R.drawable.pils;
				break;
				case 49: imageId = R.drawable.porter;
				break;
				case 50: imageId = R.drawable.porter;
				break;
				case 51: imageId = R.drawable.porter;
				break;
				case 52: imageId = R.drawable.pumpkin;
				break;
				case 53: imageId = R.drawable.black;
				break;
				case 54: imageId = R.drawable.saison;
				break;
				case 55: imageId = R.drawable.black;
				break;
				case 56: imageId = R.drawable.scotchale;
				break;
				case 57: imageId = R.drawable.paleale;
				break;
				case 58: imageId = R.drawable.americanstout;
				break;
				case 59: imageId = R.drawable.americanstout;
				break;
				case 60: imageId = R.drawable.americanstout;
				break;
				case 61: imageId = R.drawable.americanstout;
				break;
				case 62: imageId = R.drawable.oatmeal;
				break;
				case 63: imageId = R.drawable.oatmeal;
				break;
				case 64: imageId = R.drawable.dubbel;
				break;
				case 65: imageId = R.drawable.quad;
				break;
				case 66: imageId = R.drawable.tripel;
				break;
				case 67: imageId = R.drawable.viennalager;
				break;
				case 68: imageId = R.drawable.weizenbock;
				break;
				case 69: imageId = R.drawable.barleywine;
				break;
				case 70: imageId = R.drawable.witbier;
				break;
				case 71: imageId = R.drawable.fruit;
				break;
				case 72: imageId = R.drawable.oldale;
				break;
				case 73: imageId = R.drawable.black;
				break;
				case 74: imageId = R.drawable.saison;
				break;
				case 75: imageId = R.drawable.californiacommon;
				break;
				case 76: imageId = R.drawable.redipa;
				break;
				case 77: imageId = R.drawable.gose;
				break;
				}
				return imageId;
			}
		//type id to Text DESCRIPTION of type
		//Using switch statements for readability. break stops the thing early
		public String idToDescription(int id){
			String typeString = "Unknown";
			switch (id){
			case 0: typeString = "This ale features toasted malt, fruity flavors, and a crisp finish that is attributed to its unique cool-temperature fermenting strand of yiest. The big caramel flavors (and colors) in the Altbier pair wonderfully with fattier foods: aged Gouda, roasted meats, shelfish, and a wide variety of desserts. ";
			break;
			case 1: typeString = "Named for the amber hue that stems from darker malts, this is an enourmous category that covers everything from slightly sweet and nutty to bitter and hop-forward.  You typically do not want to pair with anything that would detract from the caramel malts. Think savory instead of sweet.";
			break;
			case 2: typeString = "The vast majority of mainstream beers in the late 20th century were lagers. One can deduce why, therefore, craft breweries departed ferociously from the mainstream. Now, however, craft brewing companies are embracing the past with pre-prohibition lagers or experimenting with their newer techniques on the cold fermenting yeast. Pair with barbecue, Indian, Latin American, Thai, peppery cheeses, or shellfish. ";
			break;
			case 3: typeString = "Many craft brewing companies are making wheat beers without the idiosyncratic Hefeweizen flavor profile. Often maintaining the traditional cloudy appearance with a creamy head, but instead of banana and cloves look for floral characteristics. This is American creativity right here. Eat a hamburger.";
			break;
			case 4: typeString = "Brewed with the wild Brettanomyces yeast, these ales (or sometimes lagers) can vary. Their distinguishing characteristic is a sour citrus punch of yeast that may not be for everyone. You'll either love it or you won't, drink with peppery cheese. ";
			break;
			case 5: typeString = "Acidic character generally introduced from micro-orginisms in either the mash or wild yeast. The style varies, but the acid is typically balanced by fruit flavors and a malty sweetness. Try with peppery cheese. ";
			break;
			case 6: typeString = "The American Barley Wine is associated with heavy-handed floral hops, fruity perfume, and a very high ABV. Some Barley Wines like a few years to mature, tempering their often aggressive flavor, and they are usually hopped to death, so watch out. Expect a fully body, apparent alcohol flavor, and a roaring good time. Pair with sharp cheeses or dessert. ";
			break;
			case 7: typeString = "The first beer coined a barley wine was Bass No. 1. This beer was eventually discontinued, however, because British beers are taxed by alcohol content and boy was that beer expensive. Nevertheless, the style remains, and British Barley wines now boast lower ABV and are typically more round and balanced than their legendary predecessor. Sip with sharp cheeses.";
			break;
			case 8: typeString = "A Belgian-style pale ale that hides a high ABV behind sweet fruit flavors and solid hoppiness. Throw this one in a tulip glass to enjoy its rare golden color and exquisite flavor alongside a Triple Creme cheese.";
			break;
			case 9: typeString = "Nicknamed the Champagne of the North, the Berliner Weisse is like a sparkling unsweetened lemonade. This rare sour (but not tart) ale is crafted from warm fermenting yeast and Lactobacillus bacteria.  Place this beer at the start of your meal, and please try with truffled french fries!";
			break;
			case 10: typeString = "A new, hybrid beer style with a rather complicated fermentation process that has much in common with Champagne. This beer is bone dry and very evervescent. Not Miller High-Life, sip before meals as an aperitif. ";
			break;
			case 11: typeString = "France's indigenous style meaning beer for keeping. A rich body with blonde to light brown hues and caramel-bread flavors. Try with Indian cuisine or nutty cheeses. ";
			break;
			case 12: typeString = "A strong and robust bottom fermenting brew that is thought to be a shortened version of the name Einbeck (pronounced Ein-bock). In the 17th century a brewmaster from Einbeck was placed as the head of Hofbrauhuas in Munich. There he recreated his city's famous rich, malty lager in a style symbolizing the season's turn from winter to spring. This brew complements earthy cheeses and gamey meat dishes.";
			break;
			case 13: typeString = "Varying from innovative uses of nuts, spices, fruits, and coffee to sweeten malts, Brown Ales encompass an enormous selection of beers. Look for the malty flavors that give this ale its color, but don't be surprised by the variance in hoppiness and alcohol content. There's a lot here to explore alongside some earthy cheese and meat.";
			break;
			case 14: typeString = "California Common style arose out of American ingenuity when faced with adverse conditions for cold-fermenting lager yeast. They pumped the boiling wort up to the rooftops to be rapidly cooled by the pacific air. Expect copper color, biscuits and caramel palate, and a dry hoppy finish. Sip alongside barbecue, salads, pork, poultry, fish, or shellfish. ";
			break;
			case 15: typeString = "A crossover lager/ale that is brewed at cooler temperatures but still has mainy of the fruity flavors and aromas of an ale. It is similar to a Kolsch, but has less hop bitterness. Enjoy a refreshing Cream Ale sitting on the porch in the summer with lighter foods like a stone fruit salad, Monterey Jack cheese, and BBQ chicken.";
			break;
			case 16: typeString = "An extremely hefty lager that was originally brewed by German monks for sustenance during lent. Sometimes boasting an ABV as high as 13%, malty flavors of chocolate, coffee and dark fruit characterize this style. Double down with this double bock. Enjoy with buttery cheeses, chocolate and gamey meat. ";
			break;
			case 17: typeString = "Another response to the Czech Pils from the German industrial region of Westphalia, Dortmunder is the mine worker's pennant beer. Similarly golden, light-bodied, and refreshing, this beer is a little less dry than Pils with sweet grain flavors. Sip with buttery cheeses, pork, poultry, or fish. ";
			break;
			case 18: typeString = "The original wheat beers of Bavaria were darker than the widely spread hefeweizens of today. The Dunkelweizen retains similar tasting notes that you would expect from a cloudy wheat beer but with warmer fruits and clove notes. This caramel and banana beer pairs well with heavier German dishes and fattier cheeses.";
			break;
			case 19: typeString = "Brewers freeze their bocks and remove the ice crystals in a process called freeze distillation. What is left behind is a stronger, thicker, and more malty lager. This is a potent, toffee and botterscotch brew best sipped from a snifter. Try it with German cuisine or alongside dessert. ";
			break;
			case 20: typeString = "ESB is becoming a popular style with craft brewing companies. An English stye with more grassy and herbal earthiness than the standard pale ale. Unique and intriguing characteristics that stand up nicely to a good steak.";
			break;
			case 21: typeString = "By using darker malts than their western neighbors, brewers make Flanders Oud Bruin a sour ale with date-like sweetness and distinct woody notes. Try with cheese or meat.";
			break;
			case 22: typeString = "A combination of a house blend of bacteria and yeast, light and dark barley malts, and aging for up to two years in oak tanks produces an ale nicknamed the Burgundy of Belgium. A ruby-hued, tart ale with tannins that lend it a wine-like complexity; sip your Flanders with cheese and meat.";
			break;
			case 23: typeString = "Just after harvesting, most hops are sent to be dried and perserved before they begin to decay. However, within the first 24 hours some are sent directly to the brewers to make a fresh-hop ale. Instead of an intense bitterness, look for piney and grassy characteristics. A fresh hop ale is a treat for drinkers and chefs alike who will want to pair this style with fresh game and vegetables.";
			break;
			case 24: typeString = "Lambics are one of the few styles that roll the dice on infection during fermentation. Cooled with the windows open, fermented with fruit and aged in wooden barrels, your likely to discover, a tart, funky, fruity brew. Sip with dessert. ";
			break;
			case 25: typeString = "Similar to the Berliner Weisse but with an addition of coriander and salt. A rare style that is often accompanied by bananas and other refreshing fruit characteristics, a lacy, lasting head, complex array of zesty flavors, and a crisp finish. This style pairs especially well with flavorful seafoods such as fatty sashimi or oysters on the half shell.";
			break;
			case 26: typeString = "An extra aged mixture of young an old lambics, the resulting brew is an even more intensely sour and fruity ale. Try with dessert. ";
			break;
			case 27: typeString = "A true beauty of a beer served in a curvaceous glass. Cloudy, with an intriguing aroma of citrus, clove, and bubblegum, a lasting, creamy head and a flavor to match its nose. Named for wheat and yeast, these peculiar qualities make Hefeweissens particularly well adapted to pair with food. The German pretzel is a classic, as well as cured pork, rich seafood (especially lobster), and apple tarts.";
			break;
			case 28: typeString = "A style born from both American ingenuity and Belgian tradition.  The Belgian IPA is an amazing combination of abundant hops and unique yeast strains. Look for a balance of fruity esthers, citrus, and bitterness in this style. Pair accordingly with sharp cheeses, fried foods, or spicy ethnic dishes.";
			break;
			case 29: typeString = "Also known as American dark ale. This style is related to the IPA in bitterness, but is balanced with darker malts such as chocolate and coffee. This complexity makes the Black IPA an extremely intriguing pair with savory, spicy meals. ";
			break;
			case 30: typeString = "An IPA on steroids! Extra helpings of aroma, alcohol, and bitterness. Often one of the last choices for pairings because it takes huge flavor to stand up to Double IPA's. Enjoy!";
			break;
			case 31: typeString = "IPA's are currently the signature style for craft beer in America. Not officially divided by the Beer Judge Certification Program, an East Coast style IPA, nevertheless, tends to feature more of a balance in flavor with a fair amount of malt supporting the hop bitterness.  This style does an amazing job cutting through fried foods, standing up to spicy foods, and bringing out the magic in a sweet dessert.";
			break;
			case 32: typeString = "The British Army and East India Trading Company had a long history of shipping beer to troops stationed in India. The officers supposedly all prefered porters while the civilians stationed there tended towards pale ales that featured heavy amounts of hops for preservation.  This style became the India Pale Ale.  English-style IPA's are balanced creatures with biscuits and caramel meeting hoppy zest and spice. We recommend spicy foods (Indian, Thai or Mexican), and for a real treat try pairing with desserts like carrot or chocolate cake. ";
			break;
			case 33: typeString = "With hops grown in their backyards, these brewers showcase the piney and grassy aromatic qualities of their ambrosial flowers. Not as in-your-face bitter as Southern California beers can be. Craft a special meal with these, or simply enjoy by themselves. ";
			break;
			case 34: typeString = "This style of IPA has a dark red hue stemming from the use of caramel malts. These malts are wrapped up in a hoppy citrus blanket that is sure to please your tastebuds.  Warm aromas of sweet bread, pine, and citrus are the perfect match for Thai food.";
			break;
			case 35: typeString = "When used in whiskey, rye is known to make a spirit spicier and leaner. Look for similar additions here in the Rye IPA style. These beers feature a coppery hue and sharp, distinct flavors. Enjoy!";
			break;
			case 36: typeString = "Grab a friend because we recommend sharing one of these bad boys. Intriguing, but devastating, with an insane amount of hop bitterness and ABV!";
			break;
			case 37: typeString = "IPA's are currently the signature style for craft beer in America. Not officially divided by the Beer Judge Certification Program, a West Coast style IPA, especially in San Diego, tends to have dry profiles that showcase in-your-face hops. These are zesty and bitter brews that can cut through fried foods and stand up to spicy dishes.";
			break;
			case 38: typeString = "A beautiful, happy marriage between a hop-filled IPA and a cloudy white ale. With ample amounts of both citrus and baking spices, this is a perfect option for late spring and summer!";
			break;
			case 39: typeString = "A delicate, crisp ale with a fruity, medium to assertive bitter hop profile. American craft companies use the style often for their seasonal summertime offerings, and here at Hop Shack we include the Blonde Ales in this category as well. These beers have a distinct citrus characteristic that pairs well with Caribbean-style meat and fish dishes, but their wheat notes will also stand up to surprisingly hearty meals like ribs.";
			break;
			case 40: typeString = "Legend tells us the Maibock of Hofbrauhaus was so cherished that it saved Munich from annihilation during the Thirty Years War. Golden with a nose of honey, yet enough bitterness and body to keep you warm on an early spring night, Maibock pairs well with Italian food and nutty cheeses. ";
			break;
			case 41: typeString = "Marzens were traditionally brewed in the cold month of March and stored in cellars throughout the summer. Fall was greeted with this slightly sweet, full-bodied bridge between summer and winter. Typically well-aged and well-hopped, Marzens are a fine counterpoint to Oktoberfestbier. Enjoy with German cuisine. ";
			break;
			case 42: typeString = "Southeastern Germany was one of the first areas to use cold fermentation. Long, cold storage worked magic on the beer's maturation. Over time the area became known for its dark lager, the Munich Dunkel, typically brewed with noble German hops like Tetnang and Hallertau. The style has a bready nose with rich flavors of caramel, coffee, and nuts, so consider pairing with smoky, gamey, German meat dishes.";
			break;
			case 43: typeString = "It took a while for Munich to break tradition with their dark lagers. In 1894 Spaten created the Munich Helles, a golden lager with sweet malt and a little spice from hops. Boasting a light color, this beer is a German triumph. Enjoy with buttery cheese, salad, pork, fish, or shellfish.";
			break;
			case 44: typeString = "After Sierra Nevada's first batch of Cascade hopped pale ale, American breweries began to deviate from the English style with more crisp, hoppy bitterness. This style often blurs the line between pale ale and IPA, and is, therefore, adapted to contrasting pairings.  The hops complement sweet, spicy foods such as salsa and curry.";
			break;
			case 45: typeString = "The beverage of choice for late 17th century Britain and a forerunner for all dark beers. Burly, hardworking porters carried freight from cargo ships all around town and then refueled with this liquid bread. Deep brown, coffee roasted and chocolatey, flex your arms and drink with whatever you like.";
			break;
			case 46: typeString = "Burton upon Trent, England, held the title of brewing capital of the world in the mid 19th century in large part due to its gypsum carrying water. Now, the secret is out, and the process of adding gypsum to produce the English Pale Ale is widespread. It creates a malty, earthy, fruity, and slightly hoppy profiled brew that cannot be beat when paired with fish and chips, shepheards pie, or similar pub fare.";
			break;
			case 47: typeString = "The Czech Pilsner was a truly historic beer for Europeans accostumed to dark, burly brews. Pilsner Urquell was the first creation bottom fermenting yeast in Bohemia. Sparkling gold, light-bodied and refreshing.";
			break;
			case 48: typeString = "The German response to the blazing popularity of the Czech Pilsner in the mid 19th century. Pils are Golden, grassy, and delicately bittered. Pair this adored style with Japanese cuisine, peppery cheeses, poultry, or fish.";
			break;
			case 49: typeString = "American craft brewing companies often take an imaginative approach to the porter. They may add smoke, fruit, coconut, hops, or all of the above.It's a safe bet you're in for innovation when saddling up with one of these.";
			break;
			case 50: typeString = "As the name suggests, these rich ales were originally crafted in 18th century Britain for drinking in the cold Baltic provinces. Brewers experimented with cold fermenting lager yeast and eventually produced the sweet Baltic Porter at a timewhen shipping beer was just catching on. A porter with a more restrained roasted character, try it with earthy cheese and smoked meat. ";
			break;
			case 51: typeString = "The beverage of choice for late 17th century Brittain and a forerunner of all dark beers. Porters carried freight from cargo ships all around town. The laborers were refueled with this deep brown, coffee roasted and chocolatey ale.";
			break;
			case 52: typeString = "This pumpkin beer actually dates back to the days of the 13 original American colonies when barley malts imported from England were absurdly expensive. Desperate brewers turned to anything fermentable, including pumpkins, and now craft breweries around the world use the technique to create cinnamon and nutmeg spiced brews for the autumn season.";
			break;
			case 53: typeString = "Drying barley over open fires imbues intense smokey characters. Any style can be labeled as a Rauchbier (German for smoke), but it is traditionally dark lagers that take this title. Consider pairing with barbecue, peppery cheeses, or smoked meat, game, and salmon.";
			break;
			case 54: typeString = "The Belgian Saison was orginially handed out to field laborers to quench their thirst during harvest because water was often contaminated. The style varies, but look for farm-inspired earthy tones in this thirst quenching brew. Great with Indian and Thai cuisines as well as salad and cheese. ";
			break;
			case 55: typeString = "The Schwartzbier (German for black) is a delicious, refreshing lager that maintains the roasty flavors and color of a porter or stout without the accompanying bitterness. Brewers can either dehusk the malts to tone down the burnt characters or use cold-brew techniques. Goes well with German cuisine. ";
			break;
			case 56: typeString = "A malt-forward, after-dinner winter warmer. Because of the Scottish name, brewers tend to add a smokey flavor, making this a fine complement to cheese and smoked salmon.";
			break;
			case 57: typeString = "English pale ales divided into three loose categories. Standard is anything up to 4.1 ABV and makes an easy drinking ale. Best bitter is brewed roughly up to 4.7 %. Anything higher in ABV lands you in Strong or Extra Special Bitter (ESB) territory. These mild yet bitter beers (Standard through Best) desire subdued flavors in their counterparts such as light cheeses and sandwiches.";
			break;
			case 58: typeString = "Using the Irish Stout as a launching pad, American brewing companies are quite creative with their stouts, sometimes loading them extra hops, chocolate, or aging them in whiskey barrels. While your sure to find a trace of that distinctive blackened malt character, just about everything else is up for grabs! Be adventurous. This might be good with anything. ";
			break;
			case 59: typeString = "By using roasted, blackened barley, Irish stouts attain a black hue with little sweetness. Typically low on carbonation and alcohol content, Dry Irish Stouts tend to offer a very smooth drink. Creamy, with notes of coffee and a surprisingly light body, try one with chocolate or smoked meat.";
			break;
			case 60: typeString = "This style of stout was created for export to warmer climates. To survive the overseas journey, brewers had to add extra hops to preserve freshness, much like the IPA. Thick and black with a tan head and boasting a high alchohol content, this is kind of like an Irish Stout on steroids. Try with dessert or smoked meat. ";
			break;
			case 61: typeString = "Originally brewed to win over the Russian royalty, this stout is intense. Boasting high levels of alcohol, today the Russian Imperial Stout has transformed into an American creation with heaps of hop bitterness and ABV to accompany the chocolately, roasty stout. Try with dessert.";
			break;
			case 62: typeString = "Brewers produce Milk Stouts by adding lactose, an unfermentable sugar, to their stout mix. This practice gives the style its name as well as a creamy, sweet and more balanced character. A life changing beer style, sip on its own or with dessert. ";
			break;
			case 63: typeString = "A stout with an addition of oats, obviously. This gives the beer a creamy character that lacks the sweetness associated with a milk stout. Plenty of roasted coffee and cream in this one with an outstandingly smooth sip. Try with earthy cheese and smoked meat.";
			break;
			case 64: typeString = "This style traces its heritage back to Westmalle, a revered abbey brewery that decided to depart from the witbier in 1856. They concocted a strong brown ale with a fruity character. Over the years they made it stronger and this became the dubbel.  Look for medium to full malty sweetness but never any roast, and pair this style with a dish using basil, creamy cheeses, glazed ham, or sweet desserts.";
			break;
			case 65: typeString = "Ah the Belgian Dark! Quads = Abbey ales stronger than dubbels (ABV's north of 10%) and darker than tripels.  Aromas and flavors of sweet brown bread and baking spices.  According to the high ABV and complex malt bill, pair this style with rich, strong cheeses and buckle up. ";
			break;
			case 66: typeString = "In 1919, the minister of justice for Belgium got the Vandervelde Act passed, which excluded hard alcohol sales. To quench the thirst for harder liquor, the monks at Wetmalle decided to tripple the malt in their blonde ale. Eventually, they perfected a spicy, herbal, and fruity golden beauty, the tripel. This is a versatile style when it comes to pairing.  Try a charcuterie board or just go ahead and experiment!";
			break;
			case 67: typeString = "The Vienna Lager was invented in an early attempt to improve the cold fermentation process. After some trial, brewers produced this softer malty lager with a firm bitterness obtained from pale malts. The Vienna Lager lost prominence after the Austro-Hungarian Empire broke up, but sprang up again in Mexico of all places in the late 19th century—think Dos Equis and Negra Modelo. Pair with Latin-American food.";
			break;
			case 68: typeString = "German for bock of strength. This style has a pronounced estery alcohol character, bold and complex malt characters of dark fruits, and perhaps some spice from the high ABV. German law requires that any weizenbier be made from 50% wheat malt, and Weizenbocks are typically brewed at a higher (60%-70%) rate. They are also brewed with a special yeast which lends the beer a spicy, almost clovelike flavor. If nothing German is on the menu, pair with chocolate desserts and grilled meat. ";
			break;
			case 69: typeString = "A winter warmer like barley wine, but with a generous addition of wheat. This rounds out the brew and adds a layer of tart complexity on top of the caramel sweetness. Enjoy this fluffy strong ale with pungent cheeses or grilled meat. ";
			break;
			case 70: typeString = "The Belgian-style witbier, or white ale, is a cloudy blonde wheat beer with intricate blends of coriander, nutmeg and citrus zest. A truly delicious style that compliments a wide variety of dishes including salmon, seared tuna, mussels, and pastas.";
			break;
			case 71: typeString = "A varied style in which brewers use fruit, extract or some sort of flavoring in the fermentation process. These are usually ales with a low bitterness to let the fruit shine through. Try with dessert. ";
			break;
			case 72: typeString = "An unofficial, catch-all style with high ABV. Their characteristics do not lie in any particular category. Keep an eye out as these are exciting to try and may throw you for a loop. ";
			break;
			case 73: typeString = "An uncommon Polish style in which brewers use smoked wheat and top fermenting yeast. We're talking about peat smoke here, folks. Powerful stuff. Try bravely with barbecue or peppery cheese. ";
			break;
			case 74: typeString = "An ancient Scottish ale that is seeing a slight comeback in craft beer. Instead of using hops to bitter and flavor the ale, brewers use a mixture of herbs. Look for an unusual mixture of botanical characteristics. Try it with salad or game dishes. ";
			break;
			case 75: typeString = "Another catch-all category that contains any beer that is specially spiced, ranging from chilies to cinnamon. Consult the brewing company for the particular flavors and aromas in their beer and enjoy the adventure in flavor on which your surely about to embark.";
			break;
			case 76: typeString = "Medieval German style Rye ale. Wow, can't believe you're trying one of these. You'll have to tell us how it is! Jk. Roggens are mildly hopped and full of earthy character. A throwback to the times when brewers were limited to the grains that grew around them. In Northern Germany, that was rye. Tart and refreshing, try this one instead of a hefeweizen and with a good slice of roast pork. ";
			break;
			case 77: typeString = "An unfiltered lager with flavorful up-front hops. Most Kellerbiers are served locally, straight from the casks in which they were made and aged. You must be somewhere cool if you're drinking it. Please take a picture.";
			break;
			}
			return typeString;
		}	
		
		//returns ArrayList<Hashmap<string, string>> for List of STYLES
		public ArrayList<HashMap<String, String>> returnTypeList (ArrayList<HashMap<String, String>> typeList){
			final String TAG_TYPEID = "typeId";
			final String TAG_TYPESTRING = "typeString";
			final String TAG_IMAGE="image";
			
			typeList = new ArrayList<HashMap<String, String>>();
			
			for (int i = 0; i< 78; i++){
				HashMap<String, String> can = new HashMap<String, String> ();
				can.put(TAG_IMAGE, Integer.toString(idToImage(i)));
				can.put(TAG_TYPESTRING, types[i]);
				can.put(TAG_TYPEID, Integer.toString(i));
				typeList.add(can);
			}
	    
			return typeList;
			
		}
		
//JSON string and passed in empty beerList returns Arraylist Hashmap of six random beers
public ArrayList<HashMap<String, String>> randomFlight(String jsonStr){
	    final String TAG_ID = "id";
	    final String TAG_BEER = "beer";
	    final String TAG_COMPANY = "company";
	    final String TAG_NAME = "name";
	    final String TAG_TYPE = "type";
	    final String TAG_IMAGE = "image";
	    Log.d("beerTypes","getting random flight");
	    ArrayList<HashMap<String, String>> beerList = new ArrayList<HashMap<String, String>>();
	    	
	    	if (jsonStr != null){
         	   try{
         		//Arraylist
         		   //JSON Object!
         		   JSONObject jsonObj = new JSONObject(jsonStr);
         		   //Beer JSON Array
         		   JSONArray beer = null;
         		   
         		   //JSON ARRAY node
         		   beer = jsonObj.getJSONArray(TAG_BEER);
         		   
         		   //First serving! LIGHT BEERS
         		   loop1:
         		   for (int i = 0; i<200; i++){
         			   JSONObject c = beer.getJSONObject(i);
         			  Log.d("beerTypes","getting first serving");
         			   
         			   int nType = Integer.parseInt(c.getString("type"));
         			   //I'm just grabbing name company and id for now!
         			   if(nType ==10 || nType ==9 || nType ==11 || nType ==45 || nType ==39 || nType ==15 || nType ==17 || nType ==24 || nType ==26 || nType ==25 || nType ==74 || nType ==43 || nType ==47 || nType ==48 || nType ==54){
         				  Log.d("beerTypes","found type for first serving");
         				   	String id = c.getString(TAG_ID);
	                        String name = c.getString(TAG_NAME);
	                        String company = c.getString(TAG_COMPANY);
	                        String type = c.getString(TAG_TYPE);
	                       
                            int nImage = idToImage(nType);
                            String image = String.valueOf(nImage);
	                        
	                        //Hashmap for single beer! (called can
	                        HashMap<String,String> can = new HashMap<String, String>();
	                        
	                        //add each child node to hashmap key => value
	                        
	                        can.put(TAG_ID, id);
	                        can.put(TAG_COMPANY, company);
	                        can.put(TAG_NAME, name);
	                        can.put(TAG_TYPE, type);
	                        can.put(TAG_IMAGE, image);
	                        
	                        //add can to beerList
	                        Log.d("beerTypes","adding can to beerlist");
	                        beerList.add(can);
	                        break loop1;
         		   }
         		   }
         		   //Second serving! WIERD LIGHT BEERS
         		   loop2:
         		   for (int i = 0; i<200; i++){
         			   JSONObject c = beer.getJSONObject(i);
         			   
         			   int nType = Integer.parseInt(c.getString("type"));
         			   //I'm just grabbing name company and id for now!
         			   if(nType ==1 || nType ==3 || nType ==4 || nType ==5 || nType ==14 || nType ==18 || nType ==20 || nType ==23 || nType ==71 || nType == 27 || nType ==77 || nType ==44 || nType ==46 || nType ==75 || nType ==57 || nType ==67 || nType ==70){
         				   	String id = c.getString(TAG_ID);
	                        String name = c.getString(TAG_NAME);
	                        String company = c.getString(TAG_COMPANY);
	                        String type = c.getString(TAG_TYPE);
	                        int nImage = idToImage(nType);
                            String image = String.valueOf(nImage);
                            
	                        
	                        //Hashmap for single beer! (called can
	                        HashMap<String,String> can = new HashMap<String, String>();
	                        
	                        //add each child node to hashmap key => value
	                        
	                        can.put(TAG_ID, id);
	                        can.put(TAG_COMPANY, company);
	                        can.put(TAG_NAME, name);
	                        can.put(TAG_TYPE, type);
	                        can.put(TAG_IMAGE, image);
	                        
	                        //add can to beerList
	                        beerList.add(can);
	                        break loop2;
         		   }
         		   }
         		   //Third serving! IPA's
         		   loop3:
         		   for (int i = 0; i<200; i++){
         			   JSONObject c = beer.getJSONObject(i);
         			   
         			   int nType = Integer.parseInt(c.getString("type"));
         			   //I'm just grabbing name company and id for now!
         			   if(nType ==28 || nType ==29 || nType ==31 || nType ==32 || nType ==33 || nType ==34 || nType ==35 || nType ==37 || nType ==38){
         				   	String id = c.getString(TAG_ID);
	                        String name = c.getString(TAG_NAME);
	                        String company = c.getString(TAG_COMPANY);
	                        String type = c.getString(TAG_TYPE);
	                        int nImage = idToImage(nType);
                            String image = String.valueOf(nImage);
                            
	                        
	                        //Hashmap for single beer! (called can
	                        HashMap<String,String> can = new HashMap<String, String>();
	                        
	                        //add each child node to hashmap key => value
	                        
	                        can.put(TAG_ID, id);
	                        can.put(TAG_COMPANY, company);
	                        can.put(TAG_NAME, name);
	                        can.put(TAG_TYPE, type);
                            can.put(TAG_IMAGE, image);
	                        
	                        //add can to beerList
	                        beerList.add(can);
	                        break loop3;
         		   }
         		   }
         		   //Fourth serving! BELGIAN
         		   loop4:
         		   for (int i = 0; i<200; i++){
         			   JSONObject c = beer.getJSONObject(i);
         			   
         			   int nType = Integer.parseInt(c.getString("type"));
         			   //I'm just grabbing name company and id for now!
         			   if(nType == 8 || nType == 64 || nType == 65 || nType == 66 || nType == 12 || nType == 40 || nType == 41 || nType == 42 || nType == 68){
         				   	String id = c.getString(TAG_ID);
	                        String name = c.getString(TAG_NAME);
	                        String company = c.getString(TAG_COMPANY);
	                        String type = c.getString(TAG_TYPE);
	                        int nImage = idToImage(nType);
                            String image = String.valueOf(nImage);
	                        
	                        //Hashmap for single beer! (called can
	                        HashMap<String,String> can = new HashMap<String, String>();
	                        
	                        //add each child node to hashmap key => value
	                        
	                        can.put(TAG_ID, id);
	                        can.put(TAG_COMPANY, company);
	                        can.put(TAG_NAME, name);
	                        can.put(TAG_TYPE, type);
                            can.put(TAG_IMAGE, image);
	                        
	                        //add can to beerList
	                        beerList.add(can);
	                        break loop4;
         		   }
         		   }
         		   loop5:
         		   for (int i = 0; i<200; i++){
         			   JSONObject c = beer.getJSONObject(i);
         			   
         			   int nType = Integer.parseInt(c.getString("type"));
         			   //I'm just grabbing name company and id for now!
         			   if(nType == 0 || nType == 55 || nType == 13 || nType == 16 || nType == 73 || nType == 49 || nType == 50 || nType == 51 || nType == 52 || nType == 76 || nType == 53 || nType == 55 || nType == 58 || nType == 59 || nType == 60 || nType == 62 || nType == 63){
         				   	String id = c.getString(TAG_ID);
	                        String name = c.getString(TAG_NAME);
	                        String company = c.getString(TAG_COMPANY);
	                        String type = c.getString(TAG_TYPE);
	                        int nImage = idToImage(nType);
                            String image = String.valueOf(nImage);
	                        
	                        //Hashmap for single beer! (called can
	                        HashMap<String,String> can = new HashMap<String, String>();
	                        
	                        //add each child node to hashmap key => value
	                        
	                        can.put(TAG_ID, id);
	                        can.put(TAG_COMPANY, company);
	                        can.put(TAG_NAME, name);
	                        can.put(TAG_TYPE, type);
                            can.put(TAG_IMAGE, image);
	                        
	                        //add can to beerList
	                        beerList.add(can);
	                        break loop5;
         		   }
         		   }
         		   loop6:
         		   for (int i = 0; i<200; i++){
         			   JSONObject c = beer.getJSONObject(i);
         			   
         			   int nType = Integer.parseInt(c.getString("type"));
         			   //I'm just grabbing name company and id for now!
         			   if(nType == 6 || nType == 7 || nType == 19 || nType == 21 || nType == 22 || nType == 30 || nType == 36 || nType == 56 || nType == 61 || nType == 72 || nType == 69){
         				   	String id = c.getString(TAG_ID);
	                        String name = c.getString(TAG_NAME);
	                        String company = c.getString(TAG_COMPANY);
	                        String type = c.getString(TAG_TYPE);
	                        int nImage = idToImage(nType);
                            String image = String.valueOf(nImage);
	                        
	                        //Hashmap for single beer! (called can
	                        HashMap<String,String> can = new HashMap<String, String>();
	                        
	                        //add each child node to hashmap key => value
	                        
	                        can.put(TAG_ID, id);
	                        can.put(TAG_COMPANY, company);
	                        can.put(TAG_NAME, name);
	                        can.put(TAG_TYPE, type);
                            can.put(TAG_IMAGE, image);
	                        
	                        //add can to beerList
	                        beerList.add(can);
	                        break loop6;
         		   }
         		   }
         		  }catch (JSONException e){
         		   e.printStackTrace();
         	   }
            }else{
         	   Log.e("ServiceHandler","Couldn't get data from URL");
            }
			return beerList;	
	    }
		
}

