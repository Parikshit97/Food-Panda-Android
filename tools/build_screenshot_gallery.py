"""Build a local, offline review gallery from actual emulator captures."""
from pathlib import Path
from html import escape

ROOT = Path(__file__).resolve().parents[1]
SCREENSHOTS = ROOT / "screenshots"
SCREENS = [
    ("01-MainActivity", "Welcome", "customer"),
    ("02-activity_sign_in", "Sign in", "customer"),
    ("03-activity_sign_up", "Create account", "customer"),
    ("04-useroptions", "Home dashboard", "customer"),
    ("05-offerview", "Offers", "customer"),
    ("06-resturantinfo", "Restaurant directory", "customer"),
    ("07-order", "Choose a restaurant", "customer"),
    ("08-KFC", "KFC menu", "customer"),
    ("09-cartview", "Cart", "customer"),
    ("10-bill", "Order summary", "customer"),
    ("11-tablehall", "Choose a venue", "customer"),
    ("12-KFCtablehall", "Reserve a table or hall", "customer"),
    ("13-feedback", "Feedback", "customer"),
    ("14-admin", "Administration", "admin"),
    ("15-custdbhandler", "Customer records", "admin"),
    ("16-restdbhandler", "Restaurant records", "admin"),
    ("17-pricedbhandler", "Menu and pricing", "admin"),
    ("18-orderdbhandler", "Tables and halls", "admin"),
    ("19-bookingview", "Reservation records", "admin"),
    ("20-contact-dialog", "Contact dialog", "dialog"),
    ("21-feedback-dialog", "Feedback dialog", "dialog"),
]

cards = []
for stem, title, category in SCREENS:
    after = SCREENSHOTS / "after" / (stem + ".png")
    if not after.exists():
        raise FileNotFoundError(after)
    before = SCREENSHOTS / "before" / (stem + ".png")
    bottom = SCREENSHOTS / "after" / (stem + "-bottom.png")
    comparison = (
        f'<details><summary>View original</summary><a href="before/{stem}.png">'
        f'<img loading="lazy" src="before/{stem}.png" alt="Original {escape(title)}"></a></details>'
        if before.exists() else ""
    )
    continuation = (
        f'<details><summary>See lower section</summary><a href="after/{stem}-bottom.png">'
        f'<img loading="lazy" src="after/{stem}-bottom.png" alt="Lower section of {escape(title)}"></a></details>'
        if bottom.exists() else ""
    )
    cards.append(
        f'<article data-category="{category}"><div class="caption"><span>{stem[:2]}</span>'
        f'<h2>{escape(title)}</h2></div><a href="after/{stem}.png" title="Open full-size screenshot">'
        f'<img loading="lazy" class="phone" src="after/{stem}.png" alt="Redesigned {escape(title)}">'
        f'</a>{continuation}{comparison}</article>'
    )

html = """<!doctype html><html lang="en"><meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Food Panda · UI review</title>
<style>
*{box-sizing:border-box}body{margin:0;background:#fff9f6;color:#2c1722;font:16px/1.5 system-ui,-apple-system,sans-serif}
header,main{max-width:1440px;margin:auto;padding:48px 32px}header{padding-bottom:12px}.brand{color:#d71660;font-weight:850;letter-spacing:-1px;font-size:25px}
.eyebrow{font-size:12px;font-weight:700;letter-spacing:2px;color:#806673;margin-top:38px}h1{font-size:clamp(35px,5vw,68px);letter-spacing:-3px;line-height:1.05;margin:12px 0 20px}
.intro{max-width:680px;color:#7a6570;font-size:18px}.tags{display:flex;gap:8px;flex-wrap:wrap;margin:24px 0}.tag{border:1px solid #eadbe1;border-radius:30px;padding:7px 13px;font-size:13px}
nav{display:flex;gap:10px;flex-wrap:wrap;margin:28px 0 0}button{border:1px solid #e4cbd5;background:white;color:#923756;padding:11px 20px;border-radius:30px;font:inherit;cursor:pointer}
button[aria-pressed=true]{background:#d71660;color:white;border-color:#d71660}button:focus-visible,a:focus-visible,summary:focus-visible{outline:3px solid #8c2a54;outline-offset:4px}
.grid{display:grid;grid-template-columns:repeat(3,minmax(0,1fr));gap:38px 28px}article{min-width:0}article[hidden]{display:none}.caption{display:flex;gap:12px;align-items:center;margin-bottom:14px}
.caption span{color:#d71660;font-size:12px;font-weight:700}.caption h2{font-size:17px;font-weight:600;margin:0}.phone{width:100%;display:block;border:7px solid #301c26;border-radius:28px;background:white;box-shadow:0 14px 30px #48203212}
details{margin-top:12px;border:1px solid #ebdce2;border-radius:14px;overflow:hidden;background:white}summary{padding:12px 16px;color:#934061;font-size:14px;cursor:pointer}details img{width:100%;display:block}footer{padding:40px 32px;text-align:center;color:#7a6570;font-size:13px}
@media(max-width:1000px){.grid{grid-template-columns:repeat(2,minmax(0,1fr))}}@media(max-width:600px){header,main{padding:28px 20px}.grid{grid-template-columns:1fr;gap:34px}h1{letter-spacing:-1.5px}.phone{max-width:420px;margin:auto}}
</style>
<header><div class="brand">food panda</div><div class="eyebrow">ANDROID APP · DESIGN REVIEW</div>
<h1>A fresh look.<br>Every single screen.</h1>
<p class="intro">A complete visual refresh with warm surfaces, bold pink accents, clearer forms, and a friendlier path from hungry to happy.</p>
<div class="tags"><span class="tag">19 app screens</span><span class="tag">2 dialogs</span><span class="tag">Actual emulator captures</span><span class="tag">Before &amp; after</span></div>
<p class="intro">Tap any screenshot to inspect it at full resolution. Expand the original or the lower section beneath each screen.</p>
<nav aria-label="Filter screens"><button data-filter="all" aria-pressed="true">All screens</button><button data-filter="customer" aria-pressed="false">Customer experience</button><button data-filter="admin" aria-pressed="false">Administration</button><button data-filter="dialog" aria-pressed="false">Dialogs</button></nav></header>
<main><div class="grid">CARDS</div></main><footer>Captured from Food Panda running on Pixel 3a · Android 15 (API 35). No fabricated app screenshots.</footer>
<script>document.querySelectorAll('[data-filter]').forEach(button=>button.addEventListener('click',()=>{document.querySelectorAll('[data-filter]').forEach(b=>b.setAttribute('aria-pressed',String(b===button)));document.querySelectorAll('[data-category]').forEach(card=>card.hidden=button.dataset.filter!=='all'&&card.dataset.category!==button.dataset.filter)}));</script></html>
""".replace("CARDS", "\n".join(cards))
(SCREENSHOTS / "index.html").write_text(html)
print(f"Gallery ready: {SCREENSHOTS / 'index.html'} ({len(cards)} screens and dialogs)")
