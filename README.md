# pgsc
Pokemon Go Statistics Collector - Image recognition from PG screenshots

Pokemon Go Statstics Collector from Pokemon Go screenshots outputs a comma delimited line of output for each image it discovers in a directory. The comma delimited line is all of the statistics for capture Pokemon. It will also read metadata from images if it is available. Why would anyone possibly want to do this you ask?

Pokemon is tradtionally a statistics based game. It is the hope that capturing the statistics here will help reveal strategies to increase your odds of collecting rare Pokemon. Or in some cases increase the power of your existing Pokemon. 

Usage
-----
From the command line:

pgsc [-o <filename>] -i [-i filename]

-o  The name of the output file. Default filename is standard output

-i The name of the input file. Default is * in current directory.

License
-------
GLP license
