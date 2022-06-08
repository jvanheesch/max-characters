import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {maxBytes} from "../../validators/max-bytes.validator";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-text-area-max-bytes',
  templateUrl: './text-area-max-bytes.component.html',
  styleUrls: ['./text-area-max-bytes.component.css']
})
export class TextAreaMaxBytesComponent {
  public readonly noteForm: FormGroup;

  constructor(private http: HttpClient) {
    this.noteForm = new FormGroup({
      textInBytes: new FormControl('', [maxBytes(10)]),
    });
  }

  save() {
    this.http.post('/notes', this.noteForm.value)
      .subscribe();
  }

  count() {
    return new TextEncoder().encode(this.noteForm.controls['textInBytes'].value || '').byteLength
  }
}
