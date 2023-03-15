# Assignment X Documentation

Author: SIDDHARTH SANKAR
## Project Introduction

This project deals sith the interpreter which takes the Bytecode program and traces the program execution for the x programming language. The interpreter consists of many moving parts such as the bytecode loader, runtime stack, and the virtual machine that work togethr to execute the program. The interpreter keeps track of the program counter that will help the function jump between different function calls. The code is stored temporarily in the runtime stack which is divided into different frames that indicate different functions. All the bytecodes are stored in a Hashmap table. When each bytecode is encountered, an instance of its class is created for execution.

## Execution and Development Environment

IDE: Github Codespaces <br />
Java version: 17.0.4.1
### Class Diagram

Provide a UML package & class diagram, including details for everything you added or modified. Provide enough information so that a reader would be able to understand the application (for those classes you did not modify, you may omit the details of the class, but should still include it in the class diagram, with relationships to other classes indicated). Ensure that your diagram is legible at standard screen resolutions, and does not require scrolling - break your diagram up into multiple parts if necessary.
![UMLDIAGRAM](/uml/uml.png)

## Scope of work and Project Discussion

There are a lot of moving parts in this assignment and each part depends on the other during execution. Therefore, the order in which the tasks are completed are important. I took a look at th interpreter.java file and saw that the codetable was initialized first so that the bytecodeloader can use it to create instances of the bytecodes in the .cod file.
Therefore, I started by defining skeleton class for all the bytecodes and mapped them in a hashmap in the codetable/init function.<br />
Interpreter/ByteCodes-<br />
All the bytecodes use the abtract bytecode class for its implementation, thus, setting the abstract functions that were required by all the bytecodes was my first task. I setup an init() which is used by the program() that takes in a arraylist of the arguments for each bytecodes and initializes their values.<br />
The jumpcodes such as callcode,falsebranchcode and gotocode also extend from bytecode but will need additional functions that keep track of the address. Therefore, I added a global address variable in each of the class and a setAddress() to change this address according to the labels which is done in resolveSymbolicAddresses.<br />
some bytecodes deal with frames and needed exception handlers to handle push and pop() in the framepointer stack. Bytecodes such as argscode and loadcode were responsible for creating frames in the runtimestack. For these, I used an offset variable that tracks the postion from the top of the frame. <br />
Dealing the frames was challengin while popping as we need to keep track of the framepointers stack and the runtimestack in order to avoid stackunderflow exceptions. Therefore before each pop I check the framepointers stack and the size of the runtime stack respectively. If the size of the runtimestack is equal to the element at the top of the framepointers stack, we have reached the frame boundary. Therefore, we will have to pop the frame and move to the previous frame before we can continue to pop.<br /> 
codeTable-<br />
This is the first file I started working on as the bytecodes must be loaded first for the bytecodeloader to read the file and create instances of the identified bytecodes. The first line inside the try block of interpreter.java has a codetabel.init().Therefore, I created a hashmap which has a string key and string value and mapped each bytecode declaration to its corresponding bytecode class in interpreter/bytecode.<br />
bytecodeloader-<br />
The bytecodeloader reads every line in the .cod file and identifies the bytecode. It reads the file with the help of a buffered reader and a string tokenizer that breaks down every line into tokens. The first token will always be the bytecode declaration and we use it to get the bytecode class from the codetable. Except for halt and label, all other bytecodes have arguments and these are passed into an arraylist and inititalized using the init() fucntion of their respective bytecode. An object of the program class is initialzed and bytecodes are added into it. After the reader reaches the end of the file, the resolveSymbolicAddresses() function is called to find the destination addresses of the jumpcodes.<br />
program-<br />
The program file stores all the bytecodes and its main function is to resolve all the labels which help control the flow of the execution. Certain bytecodes known as jumpcodes have parameters that specify the line to execute next. This is done by switching the program counter according to the given arguments.I initialized a hashmap that stores all the labels along with its program counter which can be used later to jump. Labels are usually used for functions which are called later. In order to identify the labels, I tried to add an if statement to identify if the bytcode is an instance of the labelcode and store it in the hashmap so that the labels are available when we resolve the addresses of CallCode, FalseBranchCode and GotoCode which have labels to specify the destination address. After adding all the labels in the hasmap, we start to read the program file and identify the other jumpcodes. For example, when an instance of callcode is found, we get the label of the callcode and search it in the hashmap as a key.The value of the key will be the program counter of the label and we set the destination address of callcode as the label pc through setAddress().  <br />
RuntimeStack-<br />
The runtime stack consists of two parts which are linked. The framepointers stack and the runtime stack itself. The runtime stack keeps track of all the variable and functions that are declared while the framepointers stack keeps track of offsets. These offsets are the position of the start of a new frame. A new frame is created when a new function is called which has arguments. We separate the arguments from the runtime stack and create a new frame where we complete the execution of the function call. After the function call, we pop the frame and store the value at the top of the runtime stack. I used a vector for the runtime stack as it is easier to access all its elements. For example, while getting elements at their particularing offsets during loading and storing.<br />
To code toString() of runtime stack was easy but figuring the frame boundaries was the real challenge. To achieve it, I first started by reading the framepointer stack for the boundaries and used it to specify the opening and closing braces while outputting the runtime stack.<br />
 Virtual Machine-<br />
The vitual machine itself doens't perform any unique function but is a way to interact with the runtime stack. Therefore, I defined many functions which will be useful in interacting with the runtime stack such as push, pop, creating a new frame, popping a frame,etc. We also use the virtual machine during the output as it is used to display the bytecodes and the runtime stack. I did this by set a dbg variable that specifies if dump is on or off. I added the if statement in executeProgram() which checks the dbg variable and toggles the toString() of the runtimestack.<br />

## Results and Conclusions

### What I Learned
I learnt a lot about how the execution of the program works and its flow. I have always wondered how about how the computer finds the function one it is called and how it continues the program after the function is emptied from the runtime stack. Now, I know that the program counter and labels are responsible for directing the execution. I also got a deeper understanding of dynamic data structures such as stacks and arraylists as I dealt with many exceptions.
### What I Could Do Better
I din't complete my program file and as a result, couldn't compelete the resolveAddresses function. Therefore, my program cannot execute the jump bytecodes which are neccessary for function calls. I also messed up on the toString() function for each bytecode and therefore, the ouptut isn't formatted. The output also doesn't work as I get a nullpointer exception from my bytecodeloader.
### Challenges I Encountered
The most amount of errors were caused by stackunderflow and nullpointer errors. As the interpreter is mainly built using stacks and arraylists, I found it difficult to keep track of all the data structures which were constantly pushing and popping.