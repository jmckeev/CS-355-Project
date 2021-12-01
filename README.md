# CS-355-Project

For this project, we will be using Java. After using socket programming to create a way for two parties to communicate with one another, we implemented the Diffie-Hellman key exchange to encrypt and decrypt messages securely. In order to further secure the Diffie-Hellman key exchange, we made sure to randomize the keys in order to protect the scheme from vulnerabilities. In order for Alice and Bob to securely compare the contents of their files, we allow Alice and Bob to hash the contents of their files locally, then send the hash value to each other with their keys. This way, they can check if the files are identical without sending the contents of their files to each other.

The code works by using a server class that will allow two clients to connect. Once two clients connect, they can each specify the name of the file they would like to compare with their partner. Once the comparison is made, a message is printed that says whether the contents of the file are the same as the other client's file or not.

With this code, we successfully allow two parties to compare whether or not the contents of their files are the same without sending the contents of the file, while also encrypting the comparison method to ensure that other parties cannot listen in on the conversation.

The libraries/packages used are: java.io.*, java.math.BigInteger, java.net.Socket, java.util.Random, java.io.BufferedReader, java.io.File, java.io.FileReader, java.security.MessageDigest, java.io.InputStreamReader, java.io.PrintWriter, java.net.ServerSocket

The security goal for this code is to allow two parties to compare files to see if they are identical, without allowing either of them or a third party seeing what is in the files. 
