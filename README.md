# General List Manager

##### [1. Decsription](#description)

## Description
The General List Manager is a program, written in Java, HTML, CSS and JavaScript, that completes all the requirements set for UCL COMP0005 : Object-Oriented Programming Coursework 3. The requirements were set out as the following:

### {
The program is for storing, searching and viewing lists of information, essentially a general list making application. While this is implemented as a web application assume it is for your own personal use and only accessible by yourself, so no user management or logging on is required. The data should be stored in files, you decided the fomat.

The user interface should be implemented as web pages viewed in a web browser, using standard HTML and CSS. You don't need to implement a sophisticated user interface, or start using CSS libraries like Bootstrap - this module is primarily about Java programming, not creating complex front-ends for web applications! Don't get side-side tracked in to spending too much time on the appearance of web pages.

It is up to you to interpret the requirement and decide the details of what you actually implement.

#### Requirement 1.
The program can store one or more lists of items. Each list has a name or label, and items might include text, an URL, an image, some other kind of data, or a combination of these such as an item with text and an image.

#### Requirement 2.
An item in a list can be a link to another list of items, making it possible to have lists of lists.

#### Requirement 3.
Item lists can be created, deleted, removed, or edited. An item can be a link to another list.

#### Requirement 4.
Items in a list can be added, removed, or edited. An item can be a link to another list.

#### Requirement 5.
One, multiple or all item lists can be searched and lists can be located by searching for the name of the list.

#### Requirement 6.
A list is automatically saved to a file in some format (you decide), so that the user does not have to explicitly load or save to a file. When a list or items are modified, the change(s) are immediately written to the file.

## List Manager Breakdown

## The Home Page
When launching the General List Manager, it displays the navigation bar, the landing screen as well as the footer. The landing screen showcases the specifications for the coursework as well as an aesthetic background. The footer links to my Github page and the navigation bar consists of 3 items; Home, View All Lists, Search.
##### [Home]
The 'Home' menu button leads to the home page and is way for the user to return to the start.
##### [View All Lists]
This forwards the user to the dashboard where they can edit the data
##### Search
The user is able to search for an item within the chosen lists.

## [View All Lists Page]

###### Top of the Page
The 'View All Data' button allows the user to view the contents of the program as a whole. Every list is displayed as a table, with the contents being items themeslves. Clicking on the lists' name will redirect the user to the contents of the specific list. If an item is linked to another list, clicking on the item's name will scroll to the lists' table that it is linked to.

###### Left Side of the Page
The user is shown the names of all the lists. A search bar is present at the top, which filters the names of the lists by displaying the ones that contains the search key. Clicking on the name of the list will open up the list contents.

###### Right Side of the Page
The List Editor allows the user to edit the lists. They can add a list by entering its name, which should only consist of letters and numbers. They can delete one or multiple lists by selecting the list name(s) and pressing the button. They can also change the name of the list by selecting the list and entering the name they wish to change it to.

## View List Page

###### Top of the Page
Adding an item to the list is done by entering the item's name and adding it the list, as long as the item name does not already exist. The item name can only consist of letters and numbers

###### Centre of the Page
The names of all the items are displayed in a table with the option to view the specific item or remove it from the list. A search bar is set at the top of the table to filter out the items given a search key. The 'View' button will display the item's contents whereas the 'Delete' button will remove the item from the list and refresh the page.

###### View an Item Page

###### Top of the Page
A button is displayed to allow the user to go back to the list the item belongs to. It also displays the name of the list it belongs to.

###### Centre of the Page
The contents of the item are displayed in a table and shows the following: item name, item link, item text, item URL, item file. Changing the item's name will redirect the user back to the list with the item's name changed. Deleting the item's name will delete the item itself. Linking an item to a list is done by selecting an option of all the lists available. Resetting an item's link results in the item being linked to nothing. The item description has a 1000 character limit and can be set or deleted via the designated buttongs. Setting the item's URL is done by inputting a valid URL. Once set, the user can click on the URL to visit it as well. Deleting it will get rid of the item's URL. The item file is set by choosing a file from your files. It will then upload the file and save it within the programs data. Once set, the file will download once clicked on. Deleting it will delete the file from the programs contents.

## Search Page
All the lists are displayed in a table at the centre of the page. They are all checked by default and can be unselected if you do not wish to search that particular list. In the case of a large number of lists, a search bar is located on top of the table to search for a list. 

Once the lists you wish to search have been selected, you can enter the search key to be searched. Results are given if the item name contains the search key irrespecitve of cases.

The search results page is displayed by a white information box stating the number of results found as well as the search query. A table is given with the item's name and the list it belongs to. Clicking on the list will redirect the user to contents of the list and clicking on the item will redirect the user to the contents of the item. A search bar is located on top of the table for convenience, if needed.

