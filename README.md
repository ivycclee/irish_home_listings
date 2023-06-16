# K00252664 - Ivy Lee - Assignment Two

# IMPORTANT NOTES FOR SETUP BETWEEN MACHINES :woozy_face:
- File > Project Structure > Artifacts > war exploded file > \
In here, make sure that under WEB-INF > classes has a "compile output" directory \
If it's empty, or has red text, you should be able to drag in from under the 'Available Elements' section just to the right \
\
As well as this, make sure the 'Web facet resources' is also dragged over **not in any directory

![image](https://user-images.githubusercontent.com/57398736/200148392-ea3e38ea-a1c8-4cda-ae8a-c4350d587f08.png) 

- Tomcat version: 9.0.68
- SDK: 17

# Week 24/10/2022 - 30/10/2022
- Initial setup; changes to pom, some sql files generated after connecting to db
- Initial change to structure of db - added foreign keys. Will need to change db further
- New SQL dump to override old version
- Models created via persistence mapping
- Implemented 'View All Properties' for non-logged in user
- Integrated datatables for easy sort
- Implemented property drilldown page
- Images show on drilldown, but need to be styled at a later date
- Header.jsp created to include in all jsp files for homogeneity

# Week 31/10/2022 - 06/11/2022
- Restructured db, added user and vendor tables + relationships
- All models are up-to-date as per db update
- Changed persistence.xml to utilise javax.persistence.jdbc.* as IntelliJ kept throwing a warning about eclipselink being deprecated
- UserDB class created
- Register user implemented with successful insert to db
- "\<property name="eclipselink.target-database" value="MySQL"/>\" needed to be added to persistence.xml to rectify a db error that kept being thrown

# Week 07/11/2022 - 13/11/2022
- Implemented logout method
- Register servlet amended to ensure for no duplicate emails (email *should* have been primary key, but didn't want to change db structure)
- Login method implemented, which checks user table first, then agent table to determine user type. This will be changed to implement Apache Shiro
- Header amended to make nav responsive, and change the navbar options based on usertype
- Manage My Properties function created, which allows an agent to view the properties that they are associated with -- this function is not available to general users or to someone who isn't logged in
- Edit Property function (available to agents only) started -- form is pre-populated with the data pulled in from db

# Week 14/11/2022 - 20/11/2022
- Add note function added to property drilldown page
- Archive changed as note needs be deleted before property can be removed from property table due to relationship
- Progress made on Edit Property function -- agent can update a property and this successfully amends db record ***however*** image capabilites have not been implemented
- Agent is able to archive a property -- this inserts a record into Archive table, and if the insert is successful this will prompt the record to be deleted from Properties table
- Agent now has the ability to view archived properties -- this link is added to the agent nav bar
- Implemented iFrame to property drilldown so that the google map embed will display
- functions.js file created with ConfirmArchive(id) function -- this function prompts the agent to confirm the archive request. PropertyID is passed through so that it can be set as a URL parameter for redirect, then the servlet can fetch this param and use it to perform db operations

# Week 21/22/2022 - 25/11/2022 (submission date) :sob: :pray:
- Logged in user can add a note on an individual property, and this is inserted into the db
- Logged in user can also edit their note, and this updates in the db
- Agent can insert a property
- Carousel implemented for images on drilldown page
- Agent can now view the vendors they are associated with
- Agent can now edit those vendors' details
- Logged in users can add a property to favourites - this is implemented via javascript function & cookies
- Users can view all their favourites one one page
- Users can remove a property from their favourites
- Agents can remove a property from their archived, which places it back into the properties table
