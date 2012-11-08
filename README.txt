(  ____ \|\     /|(  ____ \( \   \__   __/(  ____ \(  ____ )
| (    \/| )   ( || (    \/| (      ) (   | (    \/| (    )|
| (_____ | (___) || (__    | |      | |   | (__    | (____)|
(_____  )|  ___  ||  __)   | |      | |   |  __)   |     __)
      ) || (   ) || (      | |      | |   | (      | (\ (   
/\____) || )   ( || (____/\| (____/\| |   | (____/\| ) \ \__
\_______)|/     \|(_______/(_______/)_(   (_______/|/   \__/
             Storage management software 1.0
------------------------------------------------------------
SetUp:
1. Run Shelter.java (this is the server).
2. Run GUI.java (this is the user interface/client.

Client instructions:
When the program is started, you will get a login window. This window takes the user id and password of the user logging in.
If this is the first time logging in, use id: 1, and password: "admin". The fields are not case sensitive.

After successfully logging in, you will have a split view. On the left side are buttons linking to the different sections of the user interface.
These sections are: stock, order and administration.

The stock screen is where you are presented with the articles in the system. Press the "refresh" button to get the list.
If you wish to add an article to the system, press "New article" and a dialog will appear. Enter the name and amount you have of the article, and also the
shelf number where you put the article. Note that the system only has 4 shelves in this prototype(1-4). Finish by clicking "save", or press "cancel" if you
do not wish to create a new article.
To remove an article from the system, press the "deleteArticle" button. A dialog appears where you must input the article number of the article you wish to remove.
Finish by clicking "delete".
The search field and button on top can be used to search through the articles, either by article number, or by a part of the articles name.

The "New order" screen is where you will do most of your work. Enter your user id in the field labeled "user ID" and press "new order". A new order
has now been created, and you can start adding articles to it. The screen will now have 2 tables in it. The lower right one is to aid the user by
showing the user what articles are in stock, and the left one is for the articles in your order. Note that the help-table only shows how much of an article
is in stock, and not how much has been reserved by someone else using the same system.
By entering the article number of the article you wish to add to the order in the "article number" field, and the amount you need in the "amount" field, then
pressing "add to order", the article will be added. Note that if you try to add the same article twice, the first entry will be overwritten. Remove an article
from the order by entering the article number in the "article number" field and press remove from order. The amount is irrelevant here, as it removes the
article completely from the order.
When an article is added to an order, that many of the article is "reserved" by your order, and if anyone tries to add more than the "free" amount, nothing will happen.
When you are finished with your order press "Confirm order" to close the order, and print a report. The order is also moved to an archive. Otherwise press
"Cancel order" and the order will be deleted.

The administration window has to panels. The left one is to manage users in the system. Press "refresh" to get a complete list of users in the system.
Press "new user" to add a new user. Then you will get a dialog asking you for the name and password of the new user, and also which role that user has. For now
there are no difference in privileges. Enter the details and press "save" to add the user. Press "delete user" and enter the user ID of the user to remove him/her
from the system.
The right panel is the settings panel. For now, this panel only sets the IP address of the server. If you wish to chenge the server ip, enter the new ip, then
press "save". Then you need to disconnect and then connect again using the options in the menubar on top of the program. Press reset to return the settings to default.