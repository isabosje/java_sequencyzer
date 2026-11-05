# DSequencyzer

DSequencyzer is a Java command-line application for inspecting and filtering FASTA and GTF annotation files,
and summarizing tabular BLAST results. It uses [Picocli](https://picocli.info/) to parse commands and options.


## Requirements

- Java 17 or newer
- A terminal (PowerShell, Command Prompt, Linux shell, or an IDE terminal)

## Build and run

Run these commands from the project root (the directory containing `gradlew` and `build.gradle.kts`).

### Run with shadowJar

On Windows:

```powershell
java -jar build/libs/<generated-jar-name>.jar
```

On macOS/Linux:

```bash
java -jar build/libs/<generated-jar-name>.jar
```

To run a command, replace `--help` with the command and its arguments. For example:

```bash
java -jar build/libs/<generated-jar-name>.jar --help
```

On Windows, use `gradlew.bat` instead of `./gradlew`.

### Build an executable JAR

On Windows:

```powershell
.\gradlew.bat shadowJar
```

On macOS/Linux:

```bash
./gradlew shadowJar
```

The Shadow plugin creates a JAR in `build/libs` that includes the application's dependencies.
Check the exact generated filename in that directory, then run it with:

```bash
java -jar build/libs/<generated-jar-name>.jar --help
```

## General help

Display the application's available commands and options:

```bash
java -jar build/libs/<generated-jar-name>.jar --help
```

Display help for an individual command by adding `--help` after its name, for example:

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --help
```

## Commands

The examples below use the executable JAR. Replace `<generated-jar-name>.jar` with the actual filename in `build/libs`. If running through Gradle, use `./gradlew run --args="..."` (or `gradlew.bat run --args="..."` on Windows).

### `fasta` — analyze a sequence or FASTA file

The `fasta` command accepts either a DNA/RNA sequence entered directly or a FASTA file. Supported FASTA filename extensions are `.fasta`, `.fa`, and `.fna`.

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --input example.fasta --length --gc --validate
```

Options:

| Option | Description | Required (y / n) |
| --- | --- | --- |
| `-i`, `--input` | FASTA file path or nucleotide sequence. | y |
| `-l`, `--length` | Print the sequence length. | n |
| `-g`, `--gc` | Print the GC percentage. | n |
| `-va`, `--validate` | Report whether the sequence contains only valid nucleotide characters. | n |
| `--head` | Print the first 10 lines of a file. This option is for files, not sequences entered directly. | n |
| `-h`, `--help` | Show command help. | n |

Example with a sequence entered directly:

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --input ACGTACGT --length --gc --validate
```

The current validation accepts `A`, `C`, `G`, `T`, and `U` in upper- or lowercase. FASTA headers (lines starting with `>`) are skipped when the sequence is read from a FASTA file.

### `FilterCommand` — find matching GTF features

The command filters a GTF file by a gene-related attribute, chromosome, or feature type. The command name is currently `FilterCommand` (case-sensitive), as configured in the source code.

```bash
java -jar build/libs/<generated-jar-name>.jar filter --input annotations.gtf --feature gene --chromosome chr1 --strand -
java -jar build/libs/<generated-jar-name>.jar filter --input annotations.gtf --feature exon --strand +
java -jar build/libs/<generated-jar-name>.jar filter --input annotations.gtf --feature transcript --strand -
```

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Input GTF file. | y |
| `-f`, `--feature` | Feature to filter, for example `exon` or `transcript`. | n |
| `-chr`, `--chromosome` | Chromosome to filter for, for example `chr1`. | n |
| `-s`, `--strand` | Strand to filter for: `+` or `-`. | n |
| `--output-mode`  | Type of output: `FILE`, `TERMINAL` or `BOTH`. | n |
| `-o`, `--output` | Name of the output file. The `.gtf` extension is added automatically. | n |

Matching records are printed to the terminal and written to a file named `output.gtf` in the current working directory, unless the output parameter (`-o`, `--output`) is used.

### `RegionCommand` — filter a genomic region

This command selects records on a chromosome that fall entirely within the supplied start and end coordinates. 
Its current command name is `RegionCommand` (case-sensitive).

```bash
java -jar build/libs/<generated-jar-name>.jar region --input annotations.gtf -chr chr1 -s 1000 -e 5000
```

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Required input GTF file. | y |
| `-chr`, `--chromosome` | Chromosome name, for example `chr1`. | n |
| `-s`, `--start` | Start coordinate. | n |
| `-e`, `--end` | End coordinate. | n |

Matching records are printed to the terminal and written to a file named `filtered.gtf` in the current working directory, unless the output parameter (`-o`, `--output`) is used.
This allows you to choose the output filename yourself, and a `.gtf` extension will be added automatically.
The current implementation expects numeric start/end coordinates and includes only records whose start is at or after the requested start **and** whose end is at or before the requested end.

### `SummaryCommand` — count GTF feature types

```bash
java -jar build/libs/<generated-jar-name>.jar summary --input annotations.gtf
```

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Input GTF file. | y |

The command counts records by the third tab-separated GTF column (feature type) and prints the counts to the terminal. 
Lines beginning with `#` are skipped.

## Output files

Some commands create output files in the directory from which the application is run:

- `FilterCommand`: `<output>.gtf`
- `RegionCommand`: `<output>.gtf`

If the user does not enter a `-o/--output` parameter, the output file name will automatically be named `filtered.gtf`.

If an output file with the same name already exists (or if you do not enter an `-o/--output` parameter), it may be overwritten. 
Run commands from a directory where you have write permissions and keep important input files backed up.

## Project structure

```text
src/main/java/nl/bioinf/dsequencyzer/
├── Main.java                  # Picocli entry point and command registration
├── FastaFunctions.java        # FASTA reading and sequence analysis
├── GTFFunctions.java          # GTF filtering and feature counting
├── command/
│   ├── FastaCommand.java      # FASTA / nucleotide sequence analysis
│   ├── FindCommand.java       # Search GTF records by three parameters or less
│   ├── RegionCommand.java     # Filter GTF records by genomic region and chromosome
│   ├── GeneCommand.java       # Search in GTF for a gene
│   ├── SummaryCommand.java    # Count GTF feature types
│   └── ChromosomeCommand.java # Counts the various chromosomes and associated lines
└── io/
    ├── InputHandler.java      # Detect input type
    └── OutputHandler.java     # Handles output type
```

## Notes and limitations

- The commands listed above reflect the command names currently configured in the source.
  In particular, `FilterCommand`, `RegionCommand`, and `SummaryCommand` have not yet been given shorter lowercase command names.
- GTF commands expect standard tab-separated GTF records with the relevant columns present.
- Use `--help` if a command or option does not behave as expected.
- The project includes JUnit tests under `src/test`; run them with `./gradlew test` or `gradlew.bat test` on Windows. - The GTFFunctions tests are not finished yet unfortunately. 

### **Memory limitation** 

Processing very large GTF (or FASTA) files, such as a file containing the entire human genome, 
may result in an `OutOfMemoryError` due to memory limitations in the current implementation. 
The application is primarily intended for smaller input files and sequences. 

### **Memory limitation**

Processing very large GTF (or FASTA) files, such as a file containing the entire human genome,
may result in an `OutOfMemoryError` due to memory limitations in the current implementation.
The application is primarily intended for smaller input files and sequences.

## Sources

- [Java Programming Book – Michiel Noback](https://michielnoback.github.io/java_book/intro.html)
- [Java Applications – Michiel Noback](https://michielnoback.github.io/bincourses/java_apps.html)
- [NCBI – FASTA Format](https://www.ncbi.nlm.nih.gov/genbank/fastaformat/) Documentation for FASTA file format
- [Ensembl – GFF/GTF File Format](https://www.ensembl.org/info/website/upload/gff.html) Documentation for the GTF file format.

## Libraries and Dependencies

- [Picocli](https://picocli.info/) (v4.7.7) Command-line argument parsing and command handling.
- [Apache Log4j 2](https://logging.apache.org/log4j/2.x/) (v2.26.1) Logging and verbosity management.
- [JUnit Jupiter](https://junit.org/junit5/) (v6.0.0) Unit testing.
- [Gradle Shadow Plugin](https://github.com/GradleUp/shadow) (v9.6.1) Packaging the application into an executable JAR.