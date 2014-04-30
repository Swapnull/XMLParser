#XMLParser

##What is it?
An XML parser is a program that will take your xml file and then add some of the standard xml features such as the xml:id attribute to milestones and keys to names. These features make searching through your xml files quicker and easier. 
A namebase is also stored from items inside your '<name>' and '<rs>' tags. The more files you parse, the more this file evovles. The more evolved this file is, the quicker future parses will be. 

##What does it do?
- An 'XML:ID' attribute is added to your '<milestone>' tags.
- A 'key' attribute is added to all '<name>' and '<rs>' tags. This is unique to the data inside the tag. For instance '<name>John Smith</name>' will be assigned a key, say 5, and every occurance of it will change to '<name key="5">John Smith </name>'
- A change log is added inside '<revisionDesc>' that gives the date that the file was last parsed and who parsed it. 
- 