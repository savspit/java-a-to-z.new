
The app job brief:

This app search files by input keys and show result by using threads:
- First thread - scan filesystem and give filenames to Second thread
- Second thread (pool) - distributes filenames by Tasks
- Tasks (threads) - search matches accumulates and shows result


Keys:

-f : find something*
-p : path to search
-r : reader of filesystem (io или nio)
-t : number of threads which will be parsed names of files or something else in future ;)
-s : if thread found match - app will be stoped

* - necessarily to use