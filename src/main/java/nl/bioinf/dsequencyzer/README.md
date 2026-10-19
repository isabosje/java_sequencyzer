Authors: Emiel Bosma and Isa Bos

# DSequencyzer
A repository with a terminal-driven Java DNA sequence analyzer,
for short: DSequencyzer.

## Files

`Main.java`:
- This is the file you have to run to use the DSequencyzer.
  This can be done by opening a new terminal, going to the `java_sequencyzer` folder and typing `Java main.java --file --option`

`GTFFunctions.java`:
This method handles GTF-related functionalities.

It can be used for:
- Generating a summary of GTF features (misschien weglaten)
- Find specific values
- Find features within a genomic range

`ReadBLAST.java` # eventueel weglaten?

Handles reading and processing BLAST result files.

`InputHandler.java`

Determines what type of input has been given by the user.
For example, GTF files, FASTA files or a direct nucleotide sequence.
The input is then passed to the correct file handler ('FastaFunctions.java' or 'GTFFunctions.java').

`FastaFunctions.java`

Handles FASTA files and nucleotide sequences.

It can be used for operations such as:

- Reading FASTA Files
- Showing the header
- Determining a sequence's length
- Calculating GC percentage
##### PicoCLI documentatie nog toevoegen.!!!!

### Command package

This package contains the PicoCLI command classes used by DSequencyzer.

Current command include: (beschijving nog toevoegen)

- `BlastCommand`
- `FastaCommand`
- `FindCommand`
- `RegionComand`
- `SummaryCommand`

## Input Handling

The `InputHandler` first determines the type of input given by the user.
Depending on the file type, the input is passed to the correct Method.
Such as `FastaFunctions` or `GTFFunctions`.

## Output


Map maken voor output files + in .gitignore ?