from PyPDF2 import PdfReader, PdfWriter, PdfMerger

def mergePdf(pdf_list, name):
    if not name.lower().endswith(".pdf"):
        name += ".pdf"
    output_path = 'output/'+name
    with PdfMerger() as merger:
        for pdf in pdf_list:
            merger.append(pdf)
        merger.write(output_path)

    return str(output_path)

def addWatermark(pdf, watermark):

    watermark_page = PdfReader(watermark).pages[0]
    reader = PdfReader(pdf)
    writer = PdfWriter()

    for page in reader.pages:
        page.merge_page(watermark_page)
        writer.add_page(page)

    base = pdf.replace(".pdf", "")
    output = base + "_watermark.pdf"

    with open(output, "wb") as f:
        writer.write(f)

if __name__ == '__main__':
    pdf1 = 'resources/T2.02.pdf'
    pdf2 = 'resources/T2.03.pdf'
    watermark = 'resources/watermark.pdf'

    addWatermark(mergePdf([pdf1, pdf2], 'pdfUnidos'), watermark)