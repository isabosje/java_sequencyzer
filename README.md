# DSequencyzer

DSequencyzer is a Java command-line application for inspecting and filtering FASTA and GTF annotation files. It uses [Picocli](https://picocli.info/) to parse commands and options.

## Requirements

- Java 17 or newer
- A terminal (PowerShell, Command Prompt, Linux shell, or an IDE terminal)

## Build and installation

Run these commands from the project root (the directory containing `gradlew` and `build.gradle.kts`).

### Build an executable JAR

On Windows:

```powershell
.\gradlew.bat shadowJar
```

On macOS/Linux:

```bash
./gradlew shadowJar
```

The Shadow plugin creates an executable JAR in `build/libs/` that includes the application's dependencies.

Check the exact generated filename in that directory.

## Run the application

Run the executable JAR from the project root. Replace `<generated-jar-name>.jar` with the actual filename in `build/libs/`.

```bash
java -jar build/libs/<generated-jar-name>.jar
```

To check whether the application works, display the general help information:

```bash
java -jar build/libs/<generated-jar-name>.jar --help
```

If the available commands and options are displayed, the application is running correctly.

On Windows, use `gradlew.bat` instead of `./gradlew` when running commands through Gradle.

## General help

Display the application's available commands and options:

```bash
java -jar build/libs/<generated-jar-name>.jar --help
```

Display help for an individual command by adding `--help` after its name, for example:

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --help
```

## Verbosity

The application supports different verbosity levels using the `-v` or `--verbose` option.

| Option | Description |
| --- | --- |
| No option | Logging is disabled (`OFF`). |
| `-v` or `--verbose` | Shows warnings (`WARN`). |
| `-vv` | Shows information messages and warnings (`INFO`). |

The verbosity option can be added to a command to control the amount of logging information displayed.

Example:

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --input example.fasta -v
```

For more detailed logging:

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --input example.fasta -vv
```

## Commands

The examples below use the executable JAR. Replace `<generated-jar-name>.jar` with the actual filename in `build/libs/`.

If running through Gradle, use `./gradlew run --args="..."` (or `gradlew.bat run --args="..."` on Windows).

### `fasta` — analyze a sequence or FASTA file

The `fasta` command accepts either a DNA/RNA sequence entered directly or a FASTA file. Supported FASTA filename extensions are `.fasta`, `.fa`, and `.fna`.

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --input example.fasta --length --gc --validate
```

**Options:**

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | FASTA file path or nucleotide sequence. | y |
| `-l`, `--length` | Print the sequence length. | n |
| `-g`, `--gc` | Print the GC percentage. | n |
| `-va`, `--validate` | Report whether the sequence contains only valid nucleotide characters. | n |
| `--head` | Print the first 10 lines of a file. This option is for files, not sequences entered directly. | n |
| `-h`, `--help` | Show command help. | n |
| `-v`, `--verbose` | Show warning messages. Use `-vv` for information messages as well. | n |                |                    |   |

Example with a sequence entered directly:

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --input ACGTACGT --length --gc --validate
```

The current validation accepts `A`, `C`, `G`, `T`, and `U` in upper- or lowercase.
FASTA headers (lines starting with `>`) are skipped when the sequence is read from a FASTA file.

### `filter` — find matching GTF features

The command filters a GTF file by feature type, chromosome, or strand.

```bash
java -jar build/libs/<generated-jar-name>.jar filter --input annotations.gtf --feature gene --chromosome chr1 --strand -
java -jar build/libs/<generated-jar-name>.jar filter --input annotations.gtf --feature exon --strand +
java -jar build/libs/<generated-jar-name>.jar filter --input annotations.gtf --feature transcript --strand -
```

**Options:**

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Input GTF file. | y |
| `-f`, `--feature` | Feature to filter, for example `exon` or `transcript`. | n |
| `-chr`, `--chromosome` | Chromosome to filter for, for example `chr1`. | n |
| `-s`, `--strand` | Strand to filter for: `+` or `-`. | n |
| `--output-mode` | Type of output: `FILE`, `TERMINAL` or `BOTH`. | n |
| `-o`, `--output` | Name of the output file. The `.gtf` extension is added automatically. | n |
| `-v`, `--verbose` | Show warning messages. Use `-vv` for information messages as well. | n |                  |                                                                       |   |

Matching records are printed to the terminal and written to an output file, depending on the selected output mode.
If no output filename is specified, the default filename is `output.gtf`.

### `region` — filter a genomic region

This command selects records on a chromosome that overlap with the supplied start and end coordinates.

```bash
java -jar build/libs/<generated-jar-name>.jar region --input annotations.gtf -chr chr1 -s 1000 -e 5000
```

**Options:**

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Input GTF file. | y |
| `-chr`, `--chromosome` | Chromosome name, for example `chr1`. | n |
| `-s`, `--start` | Start coordinate. | n |
| `-e`, `--end` | End coordinate. | n |
| `--output-mode` | Type of output: `FILE`, `TERMINAL` or `BOTH`. | n |
| `-o`, `--output` | Name of the output file. The `.gtf` extension is added automatically. | n |
| `-v`, `--verbose` | Show warning messages. Use `-vv` for information messages as well. | n |                  |                                                                       |   |

Matching records are printed to the terminal and written to an output file. If no output filename is specified, the default filename is `filtered.gtf`.

The current implementation expects numeric start/end coordinates and includes records that overlap with the requested region.

### `summary` — count GTF feature types

```bash
java -jar build/libs/<generated-jar-name>.jar summary --input annotations.gtf
```

**Options:**

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Input GTF file. | y |
| `-v`, `--verbose` | Show warning messages. Use `-vv` for information messages as well. | n |                 |                 |   |

The command counts records by the third tab-separated GTF column (feature type) and prints the counts to the terminal.
Lines beginning with `#` are skipped.

### `gene` — display information about a specific gene

The `gene` command searches a GTF file for a specific gene using its gene ID. It displays the GTF records belonging to that gene, including its associated annotations.

```bash
java -jar build/libs/<generated-jar-name>.jar gene --input annotations.gtf --gene-id gene001
```

**Options:**

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Input GTF file. | y |
| `-g`, `--gene-id` | Gene ID to search for. | n |
| `--output-mode` | Type of output: `FILE`, `TERMINAL` or `BOTH`. | n |
| `-o`, `--output` | Name of the output file. | n |
| `-v`, `--verbose` | Show warning messages. Use `-vv` for information messages as well. | n |

The command uses the gene ID stored in the GTF attributes column to find matching records.
This can include the gene itself and related records such as transcripts and exons.

The output mode determines whether the results are written to a file, displayed in the terminal, or both.

### `chromosome` — display an overview per chromosome

The `chromosome` command provides an overview of the annotations in a GTF file for each chromosome.
It counts the chromosomes and their associated annotation records.

```bash
java -jar build/libs/<generated-jar-name>.jar chromosome --input annotations.gtf
```

**Options:**

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Input GTF file. | y |
| `-v`, `--verbose` | Show warning messages. Use `-vv` for information messages as well. | n |

The command reads the GTF file and uses the chromosome column to group and count the annotation records.
The results provide an overview of how many records are associated with each chromosome.

## Output files

The `filter`, `region` and `gene` commands create an output file named `output.gtf` by default.
The file is saved in the `output/` directory within the project directory.

You can specify a different filename using the `-o` or `--output` option.
The `.gtf` extension is added automatically, so you do not need to include it yourself.

If an output file with the same name already exists, it will be overwritten.

## Project structure

```text
src/main/java/nl/bioinf/dsequencyzer/
├── Main.java                  # Picocli entry point and command registration
├── FastaFunctions.java        # FASTA reading and sequence analysis
├── GTFFunctions.java          # GTF filtering and feature counting
├── command/
│   ├── FastaCommand.java      # FASTA / nucleotide sequence analysis
│   ├── FilterCommand.java     # Search GTF records by three parameters or less
│   ├── RegionCommand.java     # Filter GTF records by genomic region and chromosome
│   ├── GeneCommand.java       # Search in GTF for a gene
│   ├── SummaryCommand.java    # Count GTF feature types
│   ├── OutputCommand.java     # Command that defines the output options
│   ├── InputCommand.java      # Command that defines the required input file option
│   ├── VerboseCommand.java    # Command for logging verbosity
│   └── ChromosomeCommand.java # Count chromosomes and associated lines
├── io/
│   ├── InputHandler.java      # Detect input type
│   ├── OutputHandler.java     # Handles output type
│   ├── ReadFasta.java         # Utility class for reading and processing FASTA files
│   └── ReadGTF.java           # Utility class for reading and processing GTF files
└── model/
    ├── Feature.java           # Class that stores properties shared by all GTF feature types
    ├── FeatureType.java       # Enumerate class with all possible feature types
    ├── Gene.java              # Gene data type storing standard data and a list of child-transcripts
    ├── GTFParser.java         # Converts raw GTF data into objects containing associated feature objects
    ├── SimpleFeature.java     # Represents simple GTF features
    └── Transcript.java        # Transcript data type storing standard data and a list of child-features
```

## Notes and limitations

- The commands listed above reflect the lowercase command names used in the examples.
- GTF commands expect standard tab-separated GTF records with the relevant columns present.
- Use `--help` if a command or option does not behave as expected.
- The project includes JUnit tests under `src/test/`. Run them with `./gradlew test` or `gradlew.bat test` on Windows.

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