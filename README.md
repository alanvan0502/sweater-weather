# Sweater Weather
This is a sample app that showcases a number of key technologies including: REST-ful API, Retrofit, Dagger2, RxJava2, Gson, Airbnb's Epoxy Library for RecyclerViews, Android Architectural Components (Room, ViewModel, etc.) and JUnit testing.

![alt text](https://github.com/alanvan0502/sweater-weather/blob/master/readme-img/landscape-view.png?raw=true)

**1 - Highlights of App’s features and UI Components**

The app has the following features (including required features and additional features on top of the given requirements):

The app has 3 main tabs displaying Venue’s weather conditions sorted by Venue Name, Temperature and Last Updated:
These are implemented by TabLayout with the corresponding 3 Fragments that each correspond to a tab
These Fragments belong to the MainActivity and each has a RecyclerView to display weather data (each weather data point is a cell in the RecyclerView)

Each time the App starts (MainActivity is created), the data is synced from the server with the local Database. Apart from that, the User can pull on top of one of the 03 tabs to refresh the data (sync with cloud).

Clicking on each Venue row will go to the Venue Activity which contains detailed weather information about that venue.

For all tabs, the default state is un-sorted. When User clicks the menu for Sort Ascending or Sort Descending, all tabs are then sorted accordingly. For example, for the A-Z tab, the list will be sorted alphabetically in an ascending order. When User clicks Clear Sort, all tabs willbe returned to default unsorted state. However, if the data is currently being filtered, it will continue to stay filtered.

The User has the option to filter the data according to the Country. When User clicks on the Filter Button, a DialogFragment is shown on top of the MainActivity. When the User choose the Country, the data will be filtered according to that. Note that if the data is currently sorted, all sort orders will be preserved. Filter button’s onClickListener is disabled while data is being loaded (Progress bar is showing)

**2 - Summary of App’s Architecture**

Model-View-ViewModel architectural pattern is used throughout the Application. The code is divided into 5 packages (data - the data layer, injection - dagger injection module, network - fetch data from the network, ui - displaying data and interacting with user, utils - common utilities)
Network package: 
Retrofit together with Gson are used to fetch and parse the JSON data into the Model classes (Country, Data, Sport). Retrofit and Gson are used because they are well-tested and commonly used tools in the industry for this type of work.
Data package:
Model: JSON is converted into the Models and Local Database tables are created based upon the models.
Repository: The WeatherRepository acts as the handler of the data. For example, upon App initialization or User’s pull-to-refresh action, UI layer (MainActivity’s ViewModel) calls the repository to request data to be synced. The repository in turn calls the corresponding lower level classes to sync the data.
DataSource: LocalDataSource and RemoteDataSource act as the Repository’s handlers to work with both cloud and local data.
Room: Upon instantiation of the LocalDataSource, the Room Database is created (with the model classes as Tables). As the data is completely fetched from Server, Database Tables are updated.
DAO: DataAccessObjects are used to insert/update and query data to/from RoomDatabase

UI package: Contains MainActivity, MainFragments (TemperatureFragment, LastUpdatedFragment, AlphabetFragment), FilterDialogFragment, VenueActivity and their corresponding ViewModels. The activities/fragments take care of displaying and handling UI events while their viewModels take care of handling the data. 

**3 - Testing strategies**

JUnit tests are done thoroughly for all key classes which include:
 RoomDatabase: test all queries (through Dao Objects) to ensure correctness
RemoteDataSource: test the data is properly fetched
LocalDataSource: test all methods to ensure interactions with local data is correct
UI: all viewModels are tested

User interaction was tested thoroughly by hand.

**4 - Technology Stack**

Retrofit: Commonly used library for REST-ful API
Dagger2: Used to implement dependency injection which is key in ensuring clean architecture in the code
RxJava2: Used throughout the project to handle Asynchronous streams and Publishing and handling events
Gson: Used for parsing JSON
Airbnb’s Epoxy Library: Used to build RecyclerView because this library greatly improves the code that deals with RecyclerView. This makes the code cleaner, more concise and much more readable as it reduces the amount of boiler-plate codes normally needed to build RecyclerViews.
Mockito: Used to mock objects for unit testings
Android architectural components (Room and ViewModel): Used as they help ensuring the structure of the App is clear makes the App very easy to scale.

**5 - Design**

Overall, Google Material Design principles are adhered to.
