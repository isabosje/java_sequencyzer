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
java -jar build/libs/<generated-jar-name>.jar find -i <input-file-name>.gtf -f <feature> -chr <chromosome> -s <strand +/-> 
```

On macOS/Linux:

```bash
java -jar build/libs/<generated-jar-name>.jar find -i <input-file-name>.gtf -f <feature> -chr <chromosome> -s <strand +/-> 
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

The Shadow plugin creates a JAR in `build/libs/` that includes the application's dependencies.
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

The examples below use the executable JAR. Replace `<generated-jar-name>.jar` with the actual filename in `build/libs/`. If running through Gradle, use `./gradlew run --args="..."` (or `gradlew.bat run --args="..."` on Windows).

### `fasta` — analyse a sequence or FASTA file

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
| `-v`, `--validate` | Report whether the sequence contains only valid nucleotide characters. | n |
| `--head` | Print the first 10 lines of a file. This option is for files, not sequences entered directly. | n |
| `-h`, `--help` | Show command help. | n |

Example with a sequence entered directly:

```bash
java -jar build/libs/<generated-jar-name>.jar fasta --input ACGTACGT --length --gc --validate
```

The current validation accepts `A`, `C`, `G`, `T`, and `U` in upper- or lowercase. FASTA headers (lines starting with `>`) are skipped when the sequence is read from a FASTA file.

### `blast` — inspect BLAST tabular results

The `blast` command takes a BLAST result file followed by one of three analysis options:

```bash
java -jar build/libs/<generated-jar-name>.jar blast results.tsv hits
java -jar build/libs/<generated-jar-name>.jar blast results.tsv average
java -jar build/libs/<generated-jar-name>.jar blast results.tsv best
```

| Argument | Description |
| --- | --- |
| `file` | Path to a tab-separated BLAST results file. |
| `option` | `hits` counts result rows, `average` calculates the mean percentage identity, and `best` returns the highest percentage identity. |

The implementation skips blank lines and lines beginning with `#`. It reads percentage identity from the third tab-separated column, so the input file must have that column in the expected format.

### `FindCommand` — find matching GTF features

The command filters a GTF file by a gene-related attribute, chromosome, or feature type. The command name is currently `FindCommand` (case-sensitive), as configured in the source code.

```bash
java -jar build/libs/<generated-jar-name>.jar find --input annotations.gtf --option chromosome --value chr1
java -jar build/libs/<generated-jar-name>.jar find --input annotations.gtf --option feature --value exon
java -jar build/libs/<generated-jar-name>.jar find --input annotations.gtf --option gene --value ENSG000001
```

| Option | Description | Required (y/n) |
| --- | --- | --- |
| `-i`, `--input` | Input GTF file. | y |
| `-f`, `--feature` | Feature to filter, for example `exon` or `transcript`. | n |
| `-chr`, `--chromosome` | Chromosome to filter for, for example `chr1`. | n |
| `-s`, `--strand` | Strand to filter for: `+` or `-`. | n |
| `-o`, `--output` | Name of the output file. The `.gtf` extension is added automatically. | n |

Matching records are printed to the terminal and written to a file named `filtered.gtf` in the current working directory, unless the output parameter (`-o`, `--output`) is used. 
This allows you to choose the output filename yourself, and a `.gtf` extension will be added automatically.

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

- `FindCommand`: `<output>.gtf`
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
│   ├── BlastCommand.java      # BLAST result analysis
│   ├── FastaCommand.java      # FASTA / nucleotide sequence analysis
│   ├── FindCommand.java       # Search GTF records by three parameters or less
│   ├── RegionCommand.java     # Filter GTF records by genomic region and chromosome
│   ├── SummaryCommand.java    # Count GTF feature types
    └── ChromosomeCommand.java # Counts the various chromosomes and associated lines
└── io/
    ├── InputHandler.java      # Detect input type
    └── ReadBLAST.java         # Read and process BLAST result files
```

## Notes and limitations

- The commands listed above reflect the command names currently configured in the source. 
In particular, `FindCommand`, `RegionCommand`, and `SummaryCommand` have not yet been given shorter lowercase command names.
- GTF commands expect standard tab-separated GTF records with the relevant columns present.
- Use `--help` if a command or option does not behave as expected.
- The project includes JUnit tests under `src/test/`; run them with `./gradlew test` or `gradlew.bat test` on Windows. - The GTFFunctions tests are not finished yet unfortunately.