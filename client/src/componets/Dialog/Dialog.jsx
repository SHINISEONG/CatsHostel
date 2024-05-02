import '@material/web/dialog/dialog.js'
import '@material/web/dialog/_dialog.scss'
import './Dialog.scss'
const Dialog = () => {
    return (
        <md-dialog open>
            <div slot="headline">
                옵션 설정
            </div>
            <form slot="content" id="form-id" method="dialog">
                옵션을 설정하시겠습니까?
            </form>
            <div slot="actions">
                <md-text-button form="form-id">예</md-text-button>
                <md-text-button form="form-id">아니오</md-text-button>
            </div>
        </md-dialog>
    );
};

export default Dialog;