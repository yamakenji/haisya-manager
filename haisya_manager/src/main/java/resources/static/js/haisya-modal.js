// 配車の削除用フォーム
const removeRideForm = document.forms.removeRideForm;

// 削除の確認メッセージ
const removeMessage = document.getElementById('removeRideModalLabel');

// 配車の削除用モーダルを開くときの処理
document.getElementById('removeRideModal').addEventListener('show.bs.modal', (event) => {
    let removeButton = event.relatedTarget;
    let rideId = removeButton.dataset.rideId;
    let rideDate = removeButton.dataset.rideDate;

    removeRideForm.action = `/admin/rides/${rideId}/delete`;
    removeMessage.textContent = `「${rideDate}」の配車を削除してもよろしいですか？`
});