# Mobile_Application_MP1
PJAKT android course
The content of the task: 
The task is to create an application to manage, save and read an application to manage, save and read the shopping list using android data storage methods (SharedPreferences / DataStore, SQLite / Room, dataContent Provider). 
Requirements: 
- Use of a few Activity ( can be different types, such as ListActivity, PreferenceActivity) and Intent for navigation of views in the application. Local search point minimum set Activities: oMainActivity: main window for navigation, there are buttons for navigation to graphic controls. O Product list Action: list representing the list of purchases. Each element in the list should have the following information: product name, price, quantity, and indication of whether it was purchased. In addition, find the GUI elements (in place) responsible for adding new products to the list, modifying the existing ones. Recommended use RecyclerView.oOptionsActivity: screen representing the options related to the application. At least 2 (eg Color sizes of any items in the app). When restarting the app, we read the previous entry values.
- You should also browse the product list using the SQLite database (Room). Create at least one table storing all values ​​listed in the list (product name, price, quantity and designation or bought).
- Create a Content Provider that allows access to the information given.
