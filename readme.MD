Management Tree hierarchy Project

Java 1.8 version is used in the project

Steps to Run:
ManagementHierarchy is the main class to run
Input files are in resource folder (json format) that includes the json array of employees.
For running another test case, add the input file in resources folder and change the name of file in ManagementHierarchy Class.
Example:

Input-

{ "employees": [

{
  "id" :5,
  "name" : "Sarah",
  "mId" :10
},
{
  "id" :7,
  "name" : "John",
  "mId" :2
},

{
  "id" :3,
  "name" : "Jerry",
  "mId" :10
},

{
  "id" :2,
  "name" : "Mikey",
  "mId" :10
},

{
  "id" :10,
  "name" : "Tom",
  "mId" :0
}
] }

Output-

->Jerry

->->Philip

->->->David

->->->->Alice

->->->->->John

->->->->Mike

->->->Ken

->->->->Peter

->->->Tom

->->Stacie