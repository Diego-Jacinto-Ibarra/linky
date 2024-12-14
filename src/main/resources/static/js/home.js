document.querySelector('form').addEventListener('submit', async function (event) {
    event.preventDefault();
    const urlInput = document.querySelector('input[name="url"]');
    const response = await fetch('/urls/shorten', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ url: urlInput.value })
    });
    const result = await response.json();
    if (result.shortenedUrl) {
        document.getElementById('shortened-url').value = result.shortenedUrl;
        document.getElementById('result-section').classList.remove('d-none');
    } else {
        alert('Failed to shorten URL');
    }
});

document.getElementById('copy-button').addEventListener('click', function() {
    const shortenedUrlInput = document.getElementById('shortened-url');
    shortenedUrlInput.select();
    shortenedUrlInput.setSelectionRange(0, 99999); // For mobile devices
    document.execCommand('copy');
    alert('URL copied to clipboard');
});